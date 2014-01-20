package com.hyrt.cnp.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.hyrt.cnp.account.LoginActivity;
import com.hyrt.cnp.classroom.ui.ClassroomAlbumActivity;
import com.hyrt.cnp.classroom.ui.ClassroomBabayActivity;
import com.hyrt.cnp.classroom.ui.ClassroomIndexActivity;
import com.hyrt.cnp.classroom.ui.ClassroomRecipeInfoActivity;
import com.hyrt.cnp.classroom.ui.ClassroomphotolistActivity;
import com.hyrt.cnp.dynamic.ui.BabayIndexActivity;
import com.hyrt.cnp.school.ui.ClassRoomListActivity;
import com.hyrt.cnp.school.ui.SchoolIndexActivity;
import com.hyrt.cnp.school.ui.SchoolInfoActivity;
import com.hyrt.cnp.school.ui.SchoolListActivity;
import com.hyrt.cnp.school.ui.SchoolNoticeActivity;
import com.hyrt.cnp.school.ui.SchoolNoticeInfoActivity;
import com.hyrt.cnp.school.ui.SchoolPhotoActivity;
import com.hyrt.cnp.school.ui.SchoolRecipeActivity;
import com.hyrt.cnp.school.ui.SchoolRepiceInfoActivity;
import com.hyrt.cnp.school.ui.SendwordActivity;
import com.hyrt.cnp.school.ui.StarBabayActivity;
import com.hyrt.cnp.school.ui.StarTeacherActivity;
import com.hyrt.cnp.school.ui.StarTeacherInfoActivity;

/**
 * Created by GYH on 14-1-3.
 */
public class ActivityModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Named("loginactivity")
    Class loginActivity(){
        return LoginActivity.class;
    }

    @Provides
    @Named("schoollistactivity")
    Class SchoolListActivity(){
        return SchoolListActivity.class;
    }

    @Provides
    @Named("schoolIndexActivity")
    Class SchoolIndexActivity(){
        return SchoolIndexActivity.class;
    }
    @Provides
    @Named("sendwordActivity")
    Class SendwordActivity(){
        return SendwordActivity.class;
    }

    @Provides
    @Named("schoolNoticeActivity")
    Class SchoolNoticeActivity(){
        return SchoolNoticeActivity.class;
    }

    @Provides
    @Named("schoolNoticeInfoActivity")
    Class SchoolNoticeInfoActivity(){
        return SchoolNoticeInfoActivity.class;
    }
    @Provides
    @Named("schoolRecipeActivity")
    Class SchoolRecipeActivity(){
        return SchoolRecipeActivity.class;
    }
    @Provides
    @Named("starTeacherActivity")
    Class StarTeacherActivity(){
        return StarTeacherActivity.class;
    }
    @Provides
    @Named("starTeacherInfoActivity")
    Class StarTeacherInfoActivity(){
        return StarTeacherInfoActivity.class;
    }
    @Provides
    @Named("starBabayActivity")
    Class StarBabayActivity(){
        return StarBabayActivity.class;
    }
    @Provides
    @Named("classRoomListActivity")
    Class ClassRoomListActivity(){
        return ClassRoomListActivity.class;
    }
    @Provides
    @Named("schoolPhotoActivity")
    Class SchoolPhotoActivity(){
        return SchoolPhotoActivity.class;
    }
    @Provides
    @Named("schoolRepiceInfoActivity")
    Class SchoolRepiceInfoActivity(){
        return SchoolRepiceInfoActivity.class;
    }
    @Provides
    @Named("schoolInfoActivity")
    Class SchoolInfoActivity(){
        return SchoolInfoActivity.class;
    }
    @Provides
    @Named("classroomIndexActivity")
    Class ClassroomIndexActivity(){
        return ClassroomIndexActivity.class;
    }
    @Provides
    @Named("classroomAlbumActivity")
    Class classroomAlbumActivity(){
        return ClassroomAlbumActivity.class;
    }
    @Provides
    @Named("classroomphotolistActivity")
    Class classroomphotolistActivity(){
        return ClassroomphotolistActivity.class;
    }
    @Provides
    @Named("classroomBabayActivity")
    Class classroomBabayActivity(){
        return ClassroomBabayActivity.class;
    }
    @Provides
    @Named("classroomRecipeInfoActivity")
    Class classroomRecipeInfoActivity(){
        return ClassroomRecipeInfoActivity.class;
    }
    @Provides
    @Named("babayIndexActivity")
    Class babayIndexActivity(){
        return BabayIndexActivity.class;
    }
}
