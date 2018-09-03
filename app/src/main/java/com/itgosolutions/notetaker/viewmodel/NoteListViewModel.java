package com.itgosolutions.notetaker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.itgosolutions.notetaker.database.DatabaseRepo;
import com.itgosolutions.notetaker.database.NoteEntity;

import java.util.List;
import java.util.concurrent.Executor;

public class NoteListViewModel extends AndroidViewModel {

    private DatabaseRepo mRepo;
    private Executor mExecutor;

    public NoteListViewModel(@NonNull Application application,
                             DatabaseRepo databaseRepo,
                             Executor executor) {
        super(application);
        mRepo = databaseRepo;
        mExecutor = executor;
    }

    public void addSampleData() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mRepo.addSampleData();
            }
        });
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return mRepo.getAllNotes();
    }

    public void deleteAllNotes() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mRepo.deleteAllNotes();
            }
        });
    }
}
