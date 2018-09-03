package com.itgosolutions.notetaker.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.itgosolutions.notetaker.NoteTakerApplication;
import com.itgosolutions.notetaker.di.activity.ActivityComponent;
import com.itgosolutions.notetaker.di.activity.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityComponent = NoteTakerApplication.getApplicationComponent().newActivityComponent(new ActivityModule(this));
    }

    protected ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}
