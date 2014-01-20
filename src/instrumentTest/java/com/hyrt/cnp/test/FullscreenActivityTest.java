package com.hyrt.cnp.test;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;

import com.hyrt.cnp.FullscreenActivity;
import com.jayway.android.robotium.solo.Solo;

/**
 * Created by yepeng on 13-12-13.
 */
public class FullscreenActivityTest extends ActivityInstrumentationTestCase2<FullscreenActivity> {

    private Solo solo;

    @TargetApi(Build.VERSION_CODES.FROYO)
    public FullscreenActivityTest(){
        super(FullscreenActivity.class);
    }


//    @Override
//    public void setUp() throws Exception {
//        //setUp() is run before a test case is started.
//        //This is where the solo object is created.
//        solo = new Solo(getInstrumentation(), getActivity());
//    }
//
//    @Override
//    public void tearDown() throws Exception {
//        //tearDown() is run after a test case has finished.
//        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
//        solo.finishOpenedActivities();
//    }
//
//    @UiThreadTest
//    public void testHelloWorld(){
//        Spoon.screenshot(this.getActivity(),"FullscreenAcitivty");
//        solo.assertMemoryNotLow();
//        assertEquals("1", "1");
//    }


}
