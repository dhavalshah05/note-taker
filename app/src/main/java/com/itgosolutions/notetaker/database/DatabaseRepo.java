package com.itgosolutions.notetaker.database;

import android.arch.lifecycle.LiveData;

import com.itgosolutions.notetaker.utils.SampleData;

import java.util.List;

public class DatabaseRepo {

    private NoteDao mNoteDao;

    public DatabaseRepo(NoteDao noteDao) {
        mNoteDao = noteDao;
    }

    public void addSampleData() {
        mNoteDao.insertAll(SampleData.getNotes());
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return mNoteDao.getAll();
    }

    public void deleteAllNotes() {
        mNoteDao.deleteAll();
    }

    public NoteEntity getNoteById(int noteId) {
        return mNoteDao.getNoteById(noteId);
    }

    public void saveNote(NoteEntity note) {
        mNoteDao.insertNote(note);
    }

    public void deleteNote(NoteEntity note) {
        mNoteDao.deleteNote(note);
    }
}
