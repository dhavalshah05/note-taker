package com.itgosolutions.notetaker;

import android.app.Application;

import com.itgosolutions.notetaker.di.application.ApplicationComponent;
import com.itgosolutions.notetaker.di.application.ApplicationModule;
import com.itgosolutions.notetaker.di.application.DaggerApplicationComponent;

public class NoteTakerApplication extends Application {

    private static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
