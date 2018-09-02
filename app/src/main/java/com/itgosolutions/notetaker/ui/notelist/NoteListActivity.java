package com.itgosolutions.notetaker.ui.notelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.itgosolutions.notetaker.R;
import com.itgosolutions.notetaker.database.NoteEntity;
import com.itgosolutions.notetaker.ui.noteedit.NoteEditActivity;
import com.itgosolutions.notetaker.utils.SampleData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListActivity extends AppCompatActivity {

    @BindView(R.id.note_list_recycler_view)
    RecyclerView mNoteListRecyclerView;

    private List<NoteEntity> notesData = new ArrayList<>();
    private NoteListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initNoteList();

        notesData.addAll(SampleData.getNotes());
        for (NoteEntity note :
                notesData) {
            Log.i("NoteTaker", note.toString());
        }
    }

    @OnClick(R.id.fab)
    void fabClickHandler() {
        NoteEditActivity.start(this);
    }

    private void initNoteList() {
        mNoteListRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNoteListRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new NoteListAdapter(notesData);
        mNoteListRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
