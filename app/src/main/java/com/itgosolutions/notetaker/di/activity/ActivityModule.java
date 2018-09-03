package com.itgosolutions.notetaker.di.activity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final FragmentActivity mActivity;

    public ActivityModule(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
