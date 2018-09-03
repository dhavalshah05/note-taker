package com.itgosolutions.notetaker.di.activity;

import android.app.Application;
import android.arch.lifecycle.ViewModel;

import com.itgosolutions.notetaker.database.DatabaseRepo;
import com.itgosolutions.notetaker.viewmodel.NoteEditViewModel;
import com.itgosolutions.notetaker.viewmodel.NoteListViewModel;
import com.itgosolutions.notetaker.viewmodel.ViewModelFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.concurrent.Executor;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory provideViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(NoteListViewModel.class)
    ViewModel provideNoteListViewModel(Application application, DatabaseRepo repo, Executor executor) {
        return new NoteListViewModel(application, repo, executor);
    }

    @Provides
    @IntoMap
    @ViewModelKey(NoteEditViewModel.class)
    ViewModel questionsListViewModel(Application application, DatabaseRepo repo, Executor executor) {
        return new NoteEditViewModel(application, repo, executor);
    }
}
