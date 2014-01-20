package com.hyrt.cnp.classroom.ui.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hyrt.cnp.classroom.R;
import com.hyrt.cnp.classroom.ui.ClassroomRecipeInfoActivity;
import com.jayway.android.robotium.solo.Solo;
import com.jingdong.common.utils.FormatUtils;
import com.squareup.spoon.Spoon;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by GYH on 14-1-17.
 */
public class ClassroomRecipeInfoActivityTest extends ActivityInstrumentationTestCase2<ClassroomRecipeInfoActivity> {

    private Instrumentation instrumentation;
    private ClassroomRecipeInfoActivity activity;
    private TextView timetextview;
    private Button lastbutton,nextbutton;
    private ListView listView;
    private Solo solo;
    private Date date;
    public ClassroomRecipeInfoActivityTest() {
        super(ClassroomRecipeInfoActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity=getActivity();
        timetextview=(TextView)activity.findViewById(R.id.foottimetext);
        lastbutton=(Button)activity.findViewById(R.id.recipe_last);
        nextbutton=(Button)activity.findViewById(R.id.recipe_next);
        listView=(ListView)activity.findViewById(R.id.recipeinfo_listview);
        solo = new Solo(getInstrumentation(), activity);
        instrumentation = getInstrumentation();
    }

    public void testPreconditions() {

        Spoon.screenshot(activity,"ClassroomRecipeInfoActivity");
        //Try to add a message to add context to your assertions. These messages will be shown if
        //a tests fails and make it easy to understand why a test failed
        assertNotNull("mFirstTestActivity is null", activity);
        assertNotNull("timetextview is null", timetextview);
        assertNotNull("lastbutton is null", lastbutton);
        assertNotNull("nextbutton is null", nextbutton);
        assertNotNull("listView is null", listView);

    }

    public void testTimetextviewHasData(){
        String strtime =timetextview.getText().toString();
        assertNotNull("timetextview is null",strtime);
        Spoon.screenshot(activity,"timetextviewdata");
    }

    public void testTimetextviewDataCorrect(){
        String strtime =timetextview.getText().toString();
        String strtest= FormatUtils.formatDate(activity.date);
        assertEquals(strtest,strtime);
        Spoon.screenshot(activity,"timetextviewdata");
    }

    public void testListviewHasData(){
        int num=listView.getChildCount();
//        assertEquals(0,num);
        Spoon.screenshot(activity, "listviewhasdata");
    } 
    public void testclickLastbtn(){

        Calendar calendar=Calendar.getInstance();
        calendar.set(2013,4,6);
        date=calendar.getTime();
        activity.date=date;

        Spoon.screenshot(activity, "frontclicklastbtn");
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                lastbutton.performClick();
            }
        });

        instrumentation.waitForIdleSync();
        String laststr=timetextview.getText().toString();
        String strtime= FormatUtils.preDay(date);
        assertEquals(laststr,"l");
        Spoon.screenshot(activity, "behindclicklastbtn");
    }
    public void testclickNextbtn(){
        Spoon.screenshot(activity, "frontclicknextbtn");
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                nextbutton.performClick();
            }
        });
        instrumentation.waitForIdleSync();
        String nextstr=timetextview.getText().toString();
        String strtime= FormatUtils.nextDay(date);
        assertEquals(nextstr,strtime);
        Spoon.screenshot(activity, "behindclicknextbtn");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
//        solo.finishOpenedActivities();
    }
}
