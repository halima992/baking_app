package com.example.pakingapp4;

import android.os.Bundle;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v4.app.FragmentActivity;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.runner.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class testMediaFragment {


    int position = 0;
    String description = "Recipe Introduction";
    String videoURL= "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdae8_-intro-cheesecake/-intro-cheesecake.mp4";
     String thumbnailURL= "";
    @Rule
    public ActivityTestRule<MediaActivity>  activityTestRule=new ActivityTestRule<>(MediaActivity.class);

    @Before
            public void setUpFragment(){
        Bundle bundle = new Bundle();
        FragmentMedia fragmentMedia= new FragmentMedia();
        fragmentMedia.setVideo(videoURL);
        fragmentMedia.setPosition(position);
        fragmentMedia.setImage(thumbnailURL);
        fragmentMedia.setDescription(description);
        activityTestRule.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.media_container,fragmentMedia)
                .commit();


    }
    @Test
    public void descriptionView(){
        onView((withId(R.id.tv_instruction))).check(matches(isDisplayed()));
    }

}
