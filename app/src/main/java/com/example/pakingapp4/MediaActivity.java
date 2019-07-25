package com.example.pakingapp4;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.pakingapp4.model.Steps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class MediaActivity extends AppCompatActivity {
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    Steps step;
    List<Steps> steps;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        Bundle bundle = getIntent().getExtras();
        step = bundle.getParcelable("step");
        position=bundle.getInt("position");
        steps=bundle.getParcelableArrayList("lists");
        if(savedInstanceState==null){
           FragmentMedia fragmentMedia = new FragmentMedia();
            fragmentMedia.setVideo(step.getVideoURL());
            fragmentMedia.setImage(step.getThumbnailURL());
            fragmentMedia.setDescription(step.getDescription());
            fragmentMedia.setPosition(position);
            fragmentMedia.setmSteps(steps);
           FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.media_container,fragmentMedia)
                    .commit();}
    }
}

