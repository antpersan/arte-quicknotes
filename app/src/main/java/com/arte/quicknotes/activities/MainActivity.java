package com.arte.quicknotes.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.arte.quicknotes.MockNoteList;
import com.arte.quicknotes.R;
import com.arte.quicknotes.adapters.NotesAdapter;
import com.arte.quicknotes.models.Note;

public class MainActivity extends AppCompatActivity {

    private NotesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActivity();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "Acaba de resumirse la actividad", Toast.LENGTH_SHORT);
    }

    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Context context = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoteActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new NotesAdapter(MockNoteList.getInstance().getAll(), new NotesAdapter.Events() {
            @Override
            public void onNoteClicked(Note note) {
                Intent intent = new Intent(context, NoteActivity.class);
                Bundle arguments = new Bundle();
                arguments.putSerializable(NoteActivity.PARAM_NOTE, note);
                intent.putExtras(arguments);

                startActivityForResult(intent, 0);
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAdapter.notifyDataSetChanged();
    }
}
