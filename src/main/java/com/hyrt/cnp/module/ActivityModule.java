package com.hyrt.cnp.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.hyrt.cnp.account.LoginActivity;

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
}
