package com.example.dell.pinnacle.activities;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.dell.pinnacle.R;

public class Video extends AppCompatActivity {
    RelativeLayout relativeLayout;
    ImageButton close;
    VideoView videoView;
    ProgressDialog pDialog;

    String videoPath,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        init();

        Bundle bn = getIntent().getExtras();
        videoPath = (String) bn.getCharSequence("videourl");

        //Toast.makeText(this, videoPath, Toast.LENGTH_SHORT).show();

        pDialog = new ProgressDialog(this,R.style.MyTheme);
        pDialog.setTitle("Streaming Tutorial");
        pDialog.setMessage("Buffering...");
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.setMax(100);
        pDialog.show();

        try {
            MediaController mediacontroller = new MediaController(
                    Video.this);
            mediacontroller.setAnchorView(videoView);
            Uri video = Uri.parse(videoPath);
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(video);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoView.start();
            }
        });

        close.setVisibility(View.GONE);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        close.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        });
    }
    private void init() {
        close = findViewById(R.id.close);
        relativeLayout = findViewById(R.id.videoFrame);
        videoView = findViewById(R.id.video);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
