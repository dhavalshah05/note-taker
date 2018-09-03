package com.itgosolutions.notetaker.di.application;

import com.itgosolutions.notetaker.di.activity.ActivityComponent;
import com.itgosolutions.notetaker.di.activity.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    ActivityComponent newActivityComponent(ActivityModule activityModule);
}
