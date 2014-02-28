package com.hyrt.cnp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyrt.cnp.account.manager.UserMainActivity;
import com.hyrt.cnp.account.model.UserDetail;
import com.hyrt.cnp.account.request.UserDetailRequest;
import com.hyrt.cnp.account.request.UserFaceBgRequest;
import com.hyrt.cnp.account.requestListener.BaseRequestListener;
import com.hyrt.cnp.account.service.MyService;
import com.hyrt.cnp.account.utils.FaceUtils;
import com.hyrt.cnp.account.utils.FileUtils;
import com.hyrt.cnp.account.utils.PhotoUpload;
import com.hyrt.cnp.classroom.ui.ClassroomIndexActivity;
import com.hyrt.cnp.requestListener.UserFaceBgRequestListener;
import com.hyrt.cnp.school.ui.SchoolIndexActivity;
import com.hyrt.cnp.util.SystemUiHider;
import com.jingdong.app.pad.product.drawable.HandlerRecycleBitmapDrawable;
import com.jingdong.common.frame.BaseActivity;
import com.jingdong.common.utils.cache.GlobalImageCache;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import java.io.ByteArrayOutputStream;
import java.io.File;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;
import roboguice.inject.RoboInjector;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */

public class FullscreenActivity extends BaseActivity {

    @InjectView(value = R.id.my_class)
    private ImageView myClass;

    @InjectView(value = R.id.my_school)
    private ImageView mySchool;

    @InjectView(value = R.id.my_info)
    private ImageView myInfo;

    @InjectView(value = R.id.facebg_iv)
    private ImageView imageViewBg;
    @InjectView(value =R.id.intro)
    private TextView timeText;

    private PhotoUpload photoUpload;
    private Uri faceFile;
    private Bitmap bitmap;

    private boolean isLogin = false;

    public SpiceManager spiceManager1 = new SpiceManager(
            MyService.class);

    private GlobalImageCache.BitmapDigest localBitmapDigest;

    /**
     * hockeyapp plugin APP_ID for this project
     */
    private static final String APP_ID = "411f163ce21e2352706900bdccb37c92";


    @Override
    protected void onStart() {
        super.onStart();
        if (!spiceManager1.isStarted())
            spiceManager1.start(this);
    }

   /* @Override
    protected void onStop() {
        super.onStop();
        spiceManager1.shouldStop();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final RoboInjector injector = RoboGuice.getInjector(this);
        injector.injectMembersWithoutViews(this);
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
                if (isLogin) {
                    startActivity(new Intent(FullscreenActivity.this, UserMainActivity.class));
                }
            }
        });
        myClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(FullscreenActivity.this, ClassroomIndexActivity.class));
            }
        });
        findViewById(R.id.update_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faceFile = Uri.fromFile(FileUtils.createFile("cnp", "face_cover.png"));
                photoUpload = new PhotoUpload(FullscreenActivity.this, faceFile);
                photoUpload.setRang(true);
                photoUpload.choiceItem();
            }
        });
        checkForUpdates();

    }

    /**
     * 获取数据
     */
    private void initData() {
        //获取用户的基本信息
        UserDetailRequest userDetailRequest = new UserDetailRequest(this);
        spiceManager1.execute(userDetailRequest, userDetailRequest.createCacheKey(),
                1000, new BaseRequestListener(this) {

            @Override
            public BaseRequestListener start() {
                showIndeterminate(R.string.user_info_pg);
                return this;
            }

            @Override
            public void onRequestFailure(SpiceException e) {
                super.onRequestFailure(e);
                isLogin = false;
                showMessage(R.string.getinfo_msg_title, R.string.getinfod_msgerror_content);
            }

            @Override
            public void onRequestSuccess(Object o) {
                super.onRequestSuccess(o);
                if (o == null) {
                    isLogin = false;
                    showMessage(R.string.getinfo_msg_title, R.string.getinfod_msgerror_content);
                } else if (context.get() != null) {
                    isLogin = true;
                    //获取基本资料成功后，加载头像
                    FullscreenActivity fullscreenActivity = (FullscreenActivity) context.get();
                    fullscreenActivity.initFaceIfSuccess((UserDetail.UserDetailModel) o);
                }
            }
        });
    }

    /**
     * 加载图片头像
     */
    private void initFaceIfSuccess(UserDetail.UserDetailModel userData) {
        UserDetail.UserDetailModel userDetail = userData;

        //附加名字
        TextView textView = (TextView) findViewById(R.id.name_tv);
        textView.setText(userData.getData().getRenname());
        TextView timeView = (TextView) findViewById(R.id.intro);
        timeView.setText(userData.getData().getTimeText());
        //加载头像
        String facePath = FaceUtils.getAvatar(userDetail.getData().getUser_id(), FaceUtils.FACE_BIG);
        ImageView imageView = (ImageView) findViewById(R.id.face_iv);
        showDetailImage(facePath + "?time=" + userDetail.getData().getLogo(), imageView, false);

        //加载头像地址
        String faceBgPath = FaceUtils.getAvatar(userDetail.getData().getUser_id(), FaceUtils.FACE_BG);
        localBitmapDigest = showDetailImage(faceBgPath, imageViewBg, true);


    }

    /**
     * 监听剪切好的图片并上传|剪切保存好的图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PhotoUpload.PHOTO_ZOOM && data != null && data.getParcelableExtra("data") != null) {

            //保存剪切好的图片
            bitmap = data.getParcelableExtra("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            File targetFile = FileUtils.writeFile(baos.toByteArray(), "cnp", "face_cover.png");

            //上传图片资源
            UserFaceBgRequest request = new UserFaceBgRequest(this, targetFile);
            String lastRequestCacheKey = request.createCacheKey();
            UserFaceBgRequestListener userFaceRequestListener = new UserFaceBgRequestListener(this);
            spiceManager.execute(request, lastRequestCacheKey, DurationInMillis.ONE_SECOND, userFaceRequestListener.start());
        } else if (requestCode == PhotoUpload.FROM_CAMERA) {
            photoUpload.startRangPhotoZoom(faceFile);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 上传图片成功后,更新缓存中的图片
     */
    public void updateCacheAndUI() {
        if (localBitmapDigest != null) {
            GlobalImageCache.getLruBitmapCache().put(localBitmapDigest, bitmap);
            HandlerRecycleBitmapDrawable localHandlerRecycleBitmapDrawable =
                    (HandlerRecycleBitmapDrawable) imageViewBg.getDrawable();
            localHandlerRecycleBitmapDrawable.setBitmap(bitmap);
            localHandlerRecycleBitmapDrawable.invalidateSelf();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkForCrashes();
        initData();
    }

    private void checkForCrashes() {
        CrashManager.register(this, APP_ID);
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this, APP_ID);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
