package com.example.demo.fragment;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.rtp.AudioStream;
import android.os.Environment;
import android.widget.SeekBar;

import com.example.demo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

/**
 * Created by crxc on 2016/11/27.
 */
@EFragment(R.layout.fragment_music_player)
public class MusicPlayerFragment extends Fragment {
    @ViewById(R.id.seek_bar)
    SeekBar seekBar;
    private MediaPlayer player;
    private int duration;

    @Click
    void button(){
        if(player==null){
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                player.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download"+"/1.mp4");
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(player.isPlaying()){
            player.pause();
            duration = player.getDuration();
        }else {
            player.start();
        }

    }
     @Click
    void button2(){
         player.stop();
         player.release();
    }
     @Click
    void button3(){

    }
     @Click
    void button4(){

    }

}
