package com.hyrt.cnp;


import com.hyrt.cnp.account.manager.UserMainActivity;
import com.hyrt.cnp.account.request.UserDetailRequest;
import com.hyrt.cnp.account.requestListener.UserDetailRequestListener;
import com.hyrt.cnp.account.service.MyService;
import com.hyrt.cnp.classroom.ui.ClassroomIndexActivity;
import com.hyrt.cnp.school.ui.SchoolIndexActivity;
import com.hyrt.cnp.util.SystemUiHider;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends RoboActivity {

    @InjectView(value=R.id.my_class)
    private ImageView myClass;

    @InjectView(value=R.id.my_school)
    private ImageView mySchool;

    @InjectView(value=R.id.my_info)
    private ImageView myInfo;




    private SpiceManager spiceManager = new SpiceManager(
            MyService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        mySchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FullscreenActivity.this, SchoolIndexActivity.class));
            }
        });
        myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FullscreenActivity.this, UserMainActivity.class));
            }
        });
        myClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FullscreenActivity.this,ClassroomIndexActivity.class));
            }
        });
        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        UserDetailRequest userDetailRequest = new UserDetailRequest(this);
        UserDetailRequestListener userDetailRequestListener = new UserDetailRequestListener(this);
        spiceManager.execute(userDetailRequest, userDetailRequest.createCacheKey(),
                DurationInMillis.ONE_MINUTE, userDetailRequestListener.start());
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        stopService(new Intent(this,MyService.class));
        super.onStop();
    }
}
