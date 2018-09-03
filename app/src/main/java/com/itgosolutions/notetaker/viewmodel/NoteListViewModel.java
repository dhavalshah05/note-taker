package com.itgosolutions.notetaker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.itgosolutions.notetaker.database.AppRepository;
import com.itgosolutions.notetaker.database.NoteEntity;

import java.util.List;

public class NoteListViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public NoteListViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return mRepository.getAllNotes();
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }
}
