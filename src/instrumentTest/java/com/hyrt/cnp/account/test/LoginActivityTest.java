package com.hyrt.cnp.account.test;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;

import com.hyrt.cnp.account.LoginActivity;
import com.jayway.android.robotium.solo.Solo;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

//import static com.google.testing.littlemock.LittleMock.mock;

/**
 * Created by yepeng on 13-12-13.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    @Mock MappingJacksonHttpMessageConverter mockMappingJacksonHttpMessageConverter;
    @Captor ArgumentCaptor<Class<?>> clazz;
    @Captor ArgumentCaptor<HttpInputMessage> inputMessage;

    private Solo solo;

    @TargetApi(Build.VERSION_CODES.FROYO)
    public LoginActivityTest(){
        super(LoginActivity.class);
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
//        assertEquals(getActivity().getUsername(),"yepeng");
//        //tearDown() is run after a test case has finished.
//        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
//        solo.finishOpenedActivities();
//    }
//
//    @UiThreadTest
//    public void testHelloWorld(){
//        Spoon.screenshot(this.getActivity(),"LoginAcitivty");
//        solo.assertMemoryNotLow();
//        assertEquals("1", "-1");
//    }


}
