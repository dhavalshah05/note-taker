package com.itgosolutions.notetaker.ui.notelist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.itgosolutions.notetaker.R;
import com.itgosolutions.notetaker.database.NoteEntity;
import com.itgosolutions.notetaker.ui.common.BaseActivity;
import com.itgosolutions.notetaker.ui.noteedit.NoteEditActivity;
import com.itgosolutions.notetaker.viewmodel.NoteListViewModel;
import com.itgosolutions.notetaker.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListActivity extends BaseActivity implements NoteListAdapter.NoteClickListener {

    @BindView(R.id.note_list_recycler_view)
    RecyclerView mNoteListRecyclerView;

    @Inject
    ViewModelFactory viewModelFactory;
    private NoteListViewModel mViewModel;

    private NoteListAdapter mAdapter;
    private List<NoteEntity> mNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        initViewModel();
        initNoteList();

        getAllNotes();
    }

    private void getAllNotes() {
        mViewModel.getAllNotes().observe(this, new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                mNotes.clear();
                if (noteEntities != null)
                    mNotes.addAll(noteEntities);

                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(NoteListViewModel.class);
    }

    @OnClick(R.id.fab)
    void fabClickHandler() {
        NoteEditActivity.startWithCreate(this);
    }

    private void initNoteList() {
        mNoteListRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNoteListRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new NoteListAdapter(mNotes, this);
        mNoteListRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampleData();
            return true;
        } else if(id == R.id.action_delete_all) {
            deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        mViewModel.deleteAllNotes();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }

    @Override
    public void onNoteEditButtonClicked(NoteEntity note) {
        NoteEditActivity.startWithEdit(this, note.getId());
    }
}
