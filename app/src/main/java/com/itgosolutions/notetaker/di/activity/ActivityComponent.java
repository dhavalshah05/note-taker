package com.itgosolutions.notetaker.di.activity;

import com.itgosolutions.notetaker.ui.noteedit.NoteEditActivity;
import com.itgosolutions.notetaker.ui.notelist.NoteListActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ActivityModule.class, ViewModelModule.class})
public interface ActivityComponent {
    void inject(NoteListActivity noteListActivity);

    void inject(NoteEditActivity noteEditActivity);
}
