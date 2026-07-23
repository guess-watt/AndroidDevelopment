package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnPlay;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        videoView = findViewById(R.id.videoView);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String videoUrl =
                        "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";

                Uri uri = Uri.parse(videoUrl);

                videoView.setVideoURI(uri);

                MediaController mediaController =
                        new MediaController(MainActivity.this);

                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);

                videoView.start();
            }
        });
    }
}