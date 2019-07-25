package com.example.pakingapp4;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentMedia extends Fragment {
    public static final String VIDEO_URL = "video";
    public static final String IMG_URL = "image";
    public static final String DESCRIPTION  = "description";
    private boolean landScape;
    private List<Steps> mSteps;
    private String video;
    private String image;
    private String description;
    private int position;
    SimpleExoPlayer mExoPlayer;
    @BindView(R.id.tv_instruction)
    TextView textView;
    @BindView(R.id.image_step)
    ImageView imageView;
    @BindView(R.id.next)
    ImageView mbutton;
    @BindView(R.id.playerView)
    SimpleExoPlayerView mPlayerView;
    ActionBar actionBar;
    boolean TwoPane;
    public FragmentMedia(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            video=savedInstanceState.getString(VIDEO_URL);
            image=savedInstanceState.getString(IMG_URL);
            description=savedInstanceState.getString(DESCRIPTION);

        }
        actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();
        landScape=getResources().getBoolean(R.bool.landScape);
        final View rootView = inflater.inflate(R.layout.fragment_media, container, false);
        ButterKnife.bind(this,rootView);
        TwoPane= getResources().getBoolean(R.bool.TwoPane);
        if(TwoPane){
          mbutton.setVisibility(rootView.GONE);
        }
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position<mSteps.size()-1){
                    position++;

                }
                else{position=0; }
                video=mSteps.get(position).getVideoURL();
                description=mSteps.get(position).getDescription();
                image=mSteps.get(position).getThumbnailURL();
                mPlayerView.setVisibility(rootView.GONE);
                releasePlayer();
                if (mExoPlayer==null){
                    imageView.setVisibility(rootView.GONE);
                    mPlayerView.setVisibility(rootView.VISIBLE);
                    initializePlayer();
                }

                    textView.setText(description);

                    if(video.isEmpty())
                    {
                        imageView.setVisibility(rootView.VISIBLE);
                        mPlayerView.setVisibility(rootView.GONE);
                    Uri uri=Uri.parse(image);

                    Glide.with(getContext())
                            .load(uri)
                            .error(R.drawable.no_image_no_video)
                            .into(imageView);}

                Toast.makeText( getActivity(),("the button is clicked."+position+ "fix"),
                        Toast.LENGTH_SHORT).show();
            }
        });
        if(video!=null || description!=null ||image!=null ){
            mPlayerView.setVisibility(rootView.VISIBLE);
            if(mExoPlayer==null){
            initializePlayer();}
          if(video.isEmpty())
        { imageView.setVisibility(rootView.VISIBLE);
            mPlayerView.setVisibility(rootView.GONE);
            Uri uri=Uri.parse(image);
            Glide.with(getContext())
                    .load(uri)
                    .error(R.drawable.no_image_no_video)
                    .into(imageView);

        }
        textView.setText(description);

         }
        if(landScape){
            landscapeOrientation();
        }
        else{
            textView.setVisibility(View.VISIBLE);
            portraitOrientation();
            }
        return rootView;}

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(VIDEO_URL,video);
        outState.putString(IMG_URL,image);
        outState.putString(DESCRIPTION,description);

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checking the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            landscapeOrientation();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            textView.setVisibility(View.VISIBLE);
            portraitOrientation();
        }

    }
    void releasePlayer(){
        if(mExoPlayer!=null){
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;}
    }
    void initializePlayer(){
        Uri mediaUri=Uri.parse(video);
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "video");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true); }
    public void setVideo(String video){
        this.video =video;
    }
   public  void setImage(String image){
        this.image =image;
    }
    public void setDescription(String description){
        this.description =description;
    }
    public void setPosition(int position){this.position=position;}
    public void setmSteps(List<Steps> mSteps) {
        this.mSteps = mSteps;
    }

    public void landscapeOrientation(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mPlayerView.getLayoutParams();
        params.width=params.MATCH_PARENT;
        params.height=params.MATCH_PARENT;
        mPlayerView.setLayoutParams(params);
        if(actionBar!=null) {
            actionBar.hide();
        }
        getActivity().getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void portraitOrientation(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mPlayerView.getLayoutParams();
        params.width=params.MATCH_PARENT;
        params.height=600;
        mPlayerView.setLayoutParams(params);
        if(actionBar!=null) {
            actionBar.show();
        }
        getActivity().getWindow().getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


}
