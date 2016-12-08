package com.example.myapplication;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.io.File;
import java.io.IOException;

//做一个音乐播放器.带暂停和继续播放功能.并且实现拖动进度条播放歌曲指定位置功能.
public class MainActivity extends AppCompatActivity {
    private boolean isPlay=false;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/a.mp4";
    }

    public void click(View view) {
        if(mediaPlayer==null){
            mediaPlayer=new MediaPlayer();
            try {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
                setSeekBar();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    private void setSeekBar() {
        final int duration = mediaPlayer.getDuration();
        seekBar= (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(duration);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mediaPlayer.seekTo(progress);
            }
        });
        new Thread(){
            @Override
            public void run() {
                while (true){
                    SystemClock.sleep(100);
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPosition);
                    if(currentPosition>=duration)break;
                }
            }
        }.start();

    }
}
