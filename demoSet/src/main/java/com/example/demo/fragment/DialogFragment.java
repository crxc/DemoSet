package com.example.demo.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.demo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/12/5.
 */
@EFragment(R.layout.fragment_dialog)
public class DialogFragment extends Fragment {
    int select;
    ArrayList<Integer> selects;

    @Click
    void button() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("singleChoiceDialog")
//                .setMessage("傲慢与偏见")
                .setSingleChoiceItems(R.array.sms_temp, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select = which;
                    }
                }).setIcon(R.drawable.venus)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String[] stringArray = getResources().getStringArray(R.array.sms_temp);
                        Toast.makeText(getActivity(), stringArray[select], Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    @Click
    void button2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("singleChoiceDialog")
                .setMessage("傲慢与偏见")
                .setIcon(R.drawable.earth)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
        }).create().show();
    }

    @Click
    void button3() {
        selects=new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("singleChoiceDialog")
//                .setMessage("傲慢与偏见")
                .setMultiChoiceItems(R.array.sms_temp,null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                            selects.add(which);
                        else
                            selects.remove(which);
                    }
                }).setIcon(R.drawable.venus)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String[] stringArray = getResources().getStringArray(R.array.sms_temp);
                        StringBuilder sb=new StringBuilder();
                        for(int i:selects){
                            sb.append(stringArray[i])
                            .append("\r\n");
                        }
                        Toast.makeText(getActivity(), sb, Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    @Click
    void button4() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("玩命加载ing");
        progressDialog.setMax(100);
        progressDialog.setMessage("hello");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        new Thread(){
            @Override
            public void run() {
                for (int i=1;i<=100;i++){
                    progressDialog.setProgress(i);
                    SystemClock.sleep(200);
                }
                progressDialog.dismiss();
            }
        }.start();
    }
}
