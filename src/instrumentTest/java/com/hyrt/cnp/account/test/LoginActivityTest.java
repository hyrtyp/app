package com.hyrt.cnp.account.test;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;

import com.hyrt.cnp.account.LoginActivity;
import com.hyrt.cnp.account.utils.StringUtils;
import com.jayway.android.robotium.solo.Solo;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.jingdong.common.utils.FormatUtils;
import com.squareup.spoon.Spoon;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yepeng on 13-12-13.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {


    private Solo solo;

    @TargetApi(Build.VERSION_CODES.FROYO)
    public LoginActivityTest(){
        super(LoginActivity.class);
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
    public void testDataFormate(){
        Spoon.screenshot(this.getActivity(), "LoginAcitivty");
        solo.assertMemoryNotLow();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014,0,17);
        Date date = calendar.getTime();
        String dateStr = FormatUtils.formatDate(date);
        assertNotNull(dateStr);
        assertEquals(dateStr, "2014-01-17");
        String nextDateStr = FormatUtils.nextDay(date);
        assertNotNull(nextDateStr);
        assertEquals(nextDateStr, "2014-01-18");
        String preDateStr = FormatUtils.preDay(date);
        assertNotNull(preDateStr);
        assertEquals(preDateStr, "2014-01-17");

    }

    @SmallTest
    public void testMillDateFormat(){
        Spoon.screenshot(this.getActivity(), "LoginAcitivty");
        solo.assertMemoryNotLow();
        try {
            String normalTime = StringUtils.millTimeToNormalTime("1390550734");
            assertEquals(normalTime,"1970-01-17 10:15:50");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
