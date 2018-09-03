package com.itgosolutions.notetaker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.itgosolutions.notetaker.database.AppRepository;
import com.itgosolutions.notetaker.database.NoteEntity;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteEditViewModel extends AndroidViewModel {

    private MutableLiveData<NoteEntity> mNoteLiveData = new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public NoteEditViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application);
    }

    public LiveData<NoteEntity> getNoteLiveData() {
        return mNoteLiveData;
    }

    public void getNoteById(final int noteId) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity entity = mRepository.getNoteById(noteId);
                mNoteLiveData.postValue(entity);
            }
        });
    }

    public void saveNote(String noteText) {

        if (TextUtils.isEmpty(noteText.trim()))
            return;
        final NoteEntity note;

        if (mNoteLiveData.getValue() == null) {
            note = new NoteEntity(new Date(), noteText.trim());
        } else {
            note = mNoteLiveData.getValue();
            note.setText(noteText.trim());
            note.setDate(new Date());
        }

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mRepository.saveNote(note);
            }
        });

    }
}
