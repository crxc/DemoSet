package com.example.demo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.demo.R;
import com.example.demo.utils.CloseUtil;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * Created by crxc on 2016/11/30.
 */
@EFragment(R.layout.fragment_multi_thread_download)
public class MultiThreadDownloadFragment extends Fragment {
    private static final String TAG = "MultiThreadDownloadFrag";
    public  static final String COMPLETEDSIZE="CompletedSize";
    @ViewById(R.id.et_url)
    EditText et_url;
    @ViewById(R.id.et_thread_num)
    EditText et_thread_num;
    @ViewById(R.id.relative_layout)
    RelativeLayout relativeLayout;
    private int mFileSize;
    private boolean mDownloading=false;
    ProgressBar[] progressBars;
    private int threadNum;
    private boolean isFirstDown=true;
    @Click(R.id.btn_down)
    void startOrPauseDownload(){
        if(!mDownloading){
            startDownload();
            mDownloading=true;
            threadNum = Integer.valueOf(et_thread_num.getText().toString());
            if(isFirstDown) {
                initProgressBar();
                isFirstDown = false;
            }
        }
        else {
            pauseDownload();
            mDownloading=false;
        }
    }

    private void initProgressBar() {
        progressBars=new ProgressBar[threadNum];
        for(int i=0;i<threadNum;i++){
            ProgressBar progressBar= (ProgressBar) View.inflate(getActivity(), R.layout.progress_bar,null);
            RelativeLayout.LayoutParams layoutParams=
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            progressBar.setId(i+1);
            if(i==0){
                layoutParams.addRule(RelativeLayout.BELOW,R.id.btn_down);
            }
            else {
                layoutParams.addRule(RelativeLayout.BELOW,progressBars[i-1].getId());
            }
            progressBar.setLayoutParams(layoutParams);
            relativeLayout.addView(progressBar);
            progressBars[i]=progressBar;
        }
    }

    private void pauseDownload() {
        BackgroundExecutor.cancelAll("down",true);
    }
    @Background
    void startDownload() {
        String path = et_url.getText().toString();
        URL url;
        try {
            url = new URL(path);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode==200){
                mFileSize =conn.getContentLength();
                int partSize=mFileSize/threadNum;
                startMultiDown(url,partSize,threadNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startMultiDown(final URL url, final int partSize, int threadNum) {
        RandomAccessFile randomAccessFile = null;
        try {
            String s = url.getPath();
            String fileName=s.substring(s.lastIndexOf("/")+1).trim();
            if("".equals(fileName)){
                fileName= UUID.randomUUID()+".tmp";
            }
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/Download/" + fileName;
            File file=new File(path);
            randomAccessFile=new RandomAccessFile(file,"rw");
            randomAccessFile.setLength(mFileSize);
            for(int i=0;i<threadNum;i++){
                if(i==threadNum-1){
                    startDown(url,i*partSize,mFileSize-1,file,i);
                    Log.e(TAG, "startMultiDown: "+i );
                }else {
                    startDown(url,i*partSize,(i+1)*partSize-1,file,i);
                    Log.e(TAG, "startMultiDown: "+i );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(randomAccessFile);
        }
    }
    @Background(id = "down")
    void startDown(URL url,int start,int end,File file,int i){
        progressBars[i].setMax(end-start);
        RandomAccessFile randomAccessFile=null;
        InputStream inputStream=null;
        String name = Thread.currentThread().getName();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int completedSize = sharedPreferences.getInt(COMPLETEDSIZE, 0);
        int total=0;
        start+=completedSize;
        try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(5000);
                conn.setRequestProperty("Range","bytes="+start+"-"+end);
                conn.connect();

            if(conn.getResponseCode()==206){
                    inputStream = conn.getInputStream();
                    randomAccessFile=new RandomAccessFile(file,"rw");
                    randomAccessFile.seek(start);
                    byte[] b=new byte[1024];
                    int len;
                    while ((len=inputStream.read(b))>0){
                        randomAccessFile.write(b,0,len);
                        total+=len;
                        progressBars[i].setProgress(completedSize+total);
                        editor.putInt(COMPLETEDSIZE,total+completedSize).apply();
                    }
                if(new File(name).delete()){
                    Log.d(TAG, "startDown: 删除缓存成功");
                }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                CloseUtil.close(inputStream);
                CloseUtil.close(randomAccessFile);
            }
        }


}
