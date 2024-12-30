package com.example.music;

import android.media.AudioManager;
import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
   ImageButton pause,play,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pause=findViewById(R.id.pause);
        play=findViewById(R.id.play);
        stop=findViewById(R.id.s);
        MediaPlayer obj = new MediaPlayer();
        obj.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String path = "android.resource://"+getPackageName()+"/raw/kasturi";
        Uri uri = Uri.parse(path);
        try {
            obj.setDataSource(this,uri);
            obj.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.pause();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.pause();
                obj.seekTo(2);
            }
        });
    }
}