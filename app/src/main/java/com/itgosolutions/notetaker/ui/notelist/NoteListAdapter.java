package com.itgosolutions.notetaker.ui.notelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itgosolutions.notetaker.R;
import com.itgosolutions.notetaker.model.NoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {

    private final List<NoteEntity> mNotes;

    public NoteListAdapter(List<NoteEntity> mNotes) {
        this.mNotes = mNotes;
    }

    @NonNull
    @Override
    public NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListViewHolder holder, int position) {
        final NoteEntity note = mNotes.get(position);
        holder.bindNote(note);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    class NoteListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view)
        TextView mText;

        NoteListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindNote(NoteEntity note) {
            mText.setText(note.getText());
        }
    }
}
