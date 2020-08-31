package com.example.lindatoto.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lindatoto.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

public class DynamicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        String category = getIntent().getStringExtra("category");
        YouTubePlayerView youtubeplayer = findViewById(R.id.youtubeplayer);
        assert category != null;
        if (category.equals("videos")){
            final String videoId = getIntent().getStringExtra("videoid");
            youtubeplayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    assert videoId != null;
                    youTubePlayer.loadVideo(videoId, 0F);
                }
            });

        }

    }
}