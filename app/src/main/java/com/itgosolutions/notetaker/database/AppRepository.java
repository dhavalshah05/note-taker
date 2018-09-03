package com.itgosolutions.notetaker.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.itgosolutions.notetaker.utils.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
    }

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.getNoteDao().insertAll(SampleData.getNotes());
            }
        });
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return mDb.getNoteDao().getAll();
    }

    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.getNoteDao().deleteAll();
            }
        });
    }

    public NoteEntity getNoteById(int noteId) {
        return mDb.getNoteDao().getNoteById(noteId);
    }

    public void saveNote(NoteEntity note) {
        mDb.getNoteDao().insertNote(note);
    }
}
