package com.example.rvclicknew;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.Button;

import com.squareup.picasso.Picasso;

import java.io.IOException;


public class PlayActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer = new MediaPlayer();
    Button  pauseBtn;
    Button playBtn;
    ProgressBar progressBar;

    ImageView radioImage;
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        stopRadio();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_play);

        progressBar=findViewById(R.id.progresbar);
        pauseBtn = findViewById(R.id.idBtnPause);
        playBtn = findViewById(R.id.idBtnPlay);
        pauseBtn.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        radioImage=findViewById(R.id.imageView);




        TextView radioNameText = findViewById(R.id.radioNameText);
        TextView radioUrlText = findViewById(R.id.urlText);
        String radioName = "";
        String radioUrl = "";
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            radioName=extras.getString("radioName");
            radioNameText.setText(radioName);
            radioUrl= extras.getString("radioURL");
            radioUrlText.setText(radioUrl);
            String imageUri = extras.getString("radioPic");
            ImageView ivBasicImage = findViewById(R.id.imageView);
            Picasso.get().load(imageUri).into(ivBasicImage);

        }

        String finalRadioUrl = radioUrl;
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                playRadio(finalRadioUrl);
            }
        });

      pauseBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            pauseRadio();
          }
      });



    }


    private void pauseRadio(){
        mediaPlayer.pause();
        pauseBtn.setVisibility(View.INVISIBLE);
        playBtn.setVisibility(View.VISIBLE);
    }
    private void stopRadio(){
        mediaPlayer.stop();
    }
    private void playRadio(String URL) {

        String url = URL;
        progressBar.setVisibility(View.VISIBLE);
        pauseBtn.setVisibility(View.VISIBLE);
        playBtn.setVisibility(View.INVISIBLE);
        mediaPlayer.reset();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(url);

            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            mediaPlayer.start();
            progressBar.setVisibility(View.GONE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                progressBar.setSecondaryProgress(percent);
            }
        });

    }


}
