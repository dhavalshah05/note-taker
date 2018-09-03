package com.itgosolutions.notetaker.di.application;

import android.app.Application;

import com.itgosolutions.notetaker.database.AppDatabase;
import com.itgosolutions.notetaker.database.DatabaseRepo;
import com.itgosolutions.notetaker.database.NoteDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    AppDatabase provideDatabase(Application application) {
        return AppDatabase.getInstance(application);
    }

    @Singleton
    @Provides
    NoteDao provideNoteDao(AppDatabase appDatabase) {
        return appDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    DatabaseRepo provideDatabaseRepo(NoteDao noteDao) {
        return new DatabaseRepo(noteDao);
    }
}
