/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hyrt.cnp.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.service.AlbumService;
import com.hyrt.cnp.account.service.ClassRoomService;
import com.hyrt.cnp.account.service.ClassroomBabayService;
import com.hyrt.cnp.account.service.PhotoService;
import com.hyrt.cnp.account.service.RecipeInfoService;
import com.hyrt.cnp.account.service.RecipeService;
import com.hyrt.cnp.account.service.SchoolNoticeService;
import com.hyrt.cnp.account.service.SchoolService;
import com.hyrt.cnp.account.service.SendwordService;
import com.hyrt.cnp.account.service.StarBabayService;
import com.hyrt.cnp.account.service.TeacherService;
import com.hyrt.cnp.account.service.UserService;

/**
 * Provide CNP-API related services
 */
public class ServicesModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    UserService userService(CNPClient client) {
        return new UserService(client);
    }

    @Provides
    SchoolService schoolListService(CNPClient client){
        return new SchoolService(client);
    }

    @Provides
    SendwordService sendwordService(CNPClient client){
        return new SendwordService(client);
    }

    @Provides
    SchoolNoticeService schoolNoticeService(CNPClient client){
        return new SchoolNoticeService(client);
    }

    @Provides
    TeacherService teacherService(CNPClient client){
        return new TeacherService(client);
    }

    @Provides
    StarBabayService starBabayService(CNPClient client){
        return new StarBabayService(client);
    }
    @Provides
    RecipeService recipeService(CNPClient client){
        return new RecipeService(client);
    }
    @Provides
    ClassRoomService classRoomService(CNPClient client){
        return new ClassRoomService(client);
    }
    @Provides
    PhotoService photoService(CNPClient client){
        return new PhotoService(client);
    }
    @Provides
    RecipeInfoService recipeInfoService(CNPClient client){
        return new RecipeInfoService(client);
    }
    @Provides
    AlbumService albumService(CNPClient client){
        return new AlbumService(client);
    }
    @Provides
    ClassroomBabayService classroomBabayService(CNPClient client){
        return new ClassroomBabayService(client);
    }

}
