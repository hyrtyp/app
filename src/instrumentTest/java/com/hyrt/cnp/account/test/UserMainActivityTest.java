package com.hyrt.cnp.account.test;

import android.annotation.TargetApi;
import android.media.Image;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ImageView;

import com.hyrt.cnp.R;
import com.hyrt.cnp.account.manager.UserMainActivity;
import com.jayway.android.robotium.solo.Solo;
import com.squareup.spoon.Spoon;

import java.util.List;

import static org.fest.assertions.api.ANDROID.assertThat;

/**
 * Created by yepeng on 13-12-13.
 */
public class UserMainActivityTest extends ActivityInstrumentationTestCase2<UserMainActivity> {


    private Solo solo;

    @TargetApi(Build.VERSION_CODES.FROYO)
    public UserMainActivityTest(){
        super(UserMainActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    @UiThreadTest
    public void testHelloWorld(){
        Spoon.screenshot(this.getActivity(), "UserMainAcitivty");
        List views = solo.getViews();
        ImageView faceIv = (ImageView)solo.getView(R.id.user_face);
        assertNotNull(faceIv);
        assertThat(faceIv).hasDrawable(this.getActivity().getResources().getDrawable(R.drawable.iv_bg));
    }



}
