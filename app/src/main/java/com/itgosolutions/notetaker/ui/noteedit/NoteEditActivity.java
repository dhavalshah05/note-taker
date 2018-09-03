package com.itgosolutions.notetaker.ui.noteedit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.itgosolutions.notetaker.R;
import com.itgosolutions.notetaker.database.NoteEntity;
import com.itgosolutions.notetaker.viewmodel.NoteEditViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteEditActivity extends AppCompatActivity {

    private static final String NOTE_ID = "note_id";
    @BindView(R.id.note_text)
    TextView mNoteText;
    private NoteEditViewModel mViewModel;
    private int noteId;

    public static void startWithEdit(Context context, int noteId) {
        Intent intent = new Intent(context, NoteEditActivity.class);
        intent.putExtra(NOTE_ID, noteId);
        context.startActivity(intent);
    }

    public static void startWithCreate(Context context) {
        Intent intent = new Intent(context, NoteEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            noteId = bundle.getInt(NOTE_ID);
        }
        getSupportActionBar().setTitle(noteId == 0 ? "New Note" : "Edit Note");
        initViewModel();
        if (noteId > 0) {
            mViewModel.getNoteById(noteId);
        }
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(NoteEditViewModel.class);

        mViewModel.getNoteLiveData().observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(@Nullable NoteEntity noteEntity) {
                if (noteEntity != null && noteId > 0)
                    bindNote(noteEntity);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveNoteAndFinish(mNoteText.getText().toString());
        }
        return true;
    }

    private void saveNoteAndFinish(String noteText) {
        mViewModel.saveNote(noteText);
        finish();
    }

    private void bindNote(NoteEntity note) {
        mNoteText.setText(note.getText());
    }
}
