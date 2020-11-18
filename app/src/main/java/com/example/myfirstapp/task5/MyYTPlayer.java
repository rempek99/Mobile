package com.example.myfirstapp.task5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;

import com.example.myfirstapp.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;

public class MyYTPlayer extends YouTubeBaseActivity {

    private Button playButton;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;
    private YouTubePlayer myYouTubePlayer;
    private String[] video_url = {"izGwDsrQ1eQ", "zH0bqu3Hg2k", "LDU_Txk06tM", "XxXincorrectXxX"};
    private int video_chosen;
    private TextView playerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task5_activity_you_tube_player);
        video_chosen = 0;

        youTubePlayerView = findViewById(R.id.youtubePlayer);

        playerStateChangeListener = new MyPlayerStateChangeListener();

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
                myYouTubePlayer = youTubePlayer;
                playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        play();
                    }
                });
                play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(@GOOGLE_KEY, onInitializedListener);

            }
        });
        RadioGroup videoChooser = findViewById(R.id.videoChooser);
        videoChooser.setOnCheckedChangeListener(new VideoChooserListener());
        final Button pauseButton = findViewById(R.id.pauseButton);
        final Button stopButton = findViewById(R.id.stopButton);
        playerStatus = findViewById(R.id.playerStatusTextView);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

    }
    public void play(){
        if(myYouTubePlayer != null)
        myYouTubePlayer.loadVideo(video_url[video_chosen]);
        playerStatus.setText(getResources().getString(R.string.video) + getResources().getString(R.string.running));
    }
    public void pause(){
        if(myYouTubePlayer == null )
            return;
        if(myYouTubePlayer.isPlaying()){
                myYouTubePlayer.pause();
                playerStatus.setText(getResources().getString(R.string.video) + getResources().getString(R.string.paused));
            }
            else {
                myYouTubePlayer.play();
                playerStatus.setText(getResources().getString(R.string.video) + getResources().getString(R.string.running));
            }
    }
    public void stop(){
        if(myYouTubePlayer==null)
            return;
        myYouTubePlayer.release();
        myYouTubePlayer = null;
        playerStatus.setText(getResources().getString(R.string.video) + getResources().getString(R.string.stopped));
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(@GOOGLE_KEY, onInitializedListener);

            }
        });
    }

    public class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            playerStatus.setText(getResources().getString(R.string.video) + getResources().getString(R.string.loading));
        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {
            playerStatus.setText(getResources().getString(R.string.video) + getResources().getString(R.string.running));
        }

        @Override
        public void onVideoEnded() {
            playerStatus.setText(getResources().getString(R.string.video) + getResources().getString(R.string.ended));
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            playerStatus.setText(getResources().getString(R.string.video) +  errorReason.name());
        }
    }


    public class VideoChooserListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.v1radioButton:
                    video_chosen = 0;
                    break;
                case R.id.v2radioButton:
                    video_chosen = 1;
                    break;
                case R.id.v3radioButton:
                    video_chosen = 2;
                    break;
                case R.id.v4radioButton:
                    video_chosen = 3;
                    break;
            }
        }
    }
}
