package com.arte.quicknotes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arte.quicknotes.R;
import com.arte.quicknotes.models.Note;

import java.util.List;

/**
 * Created by antpersan on 27/4/16.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNoteList;

    public NotesAdapter(List<Note> notes) {
        mNoteList = notes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvExcerpt.setText(note.getExcerpt());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvExcerpt;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.note_title);
            tvExcerpt = (TextView) itemView.findViewById(R.id.note_excerpt);
        }
    }
}
