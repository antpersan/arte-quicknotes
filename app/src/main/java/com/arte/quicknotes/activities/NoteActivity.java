package com.arte.quicknotes.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.arte.quicknotes.MockNoteList;
import com.arte.quicknotes.R;
import com.arte.quicknotes.models.Note;

public class NoteActivity extends AppCompatActivity {

    public static final String PARAM_NOTE = "nota";
    private EditText mTitle;
    private EditText mContent;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        setupActivity();

        loadNote();
    }

    private void loadNote() {
        Note note = (Note) getIntent().getSerializableExtra(PARAM_NOTE);

        if (note == null) {
            return;
        }

        this.mTitle.setText(note.getTitle());
        this.mContent.setText(note.getContent());
        mNote = note;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);

        if (mNote == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveNote();
                return true;
            case R.id.action_delete:
                deleteNote();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteNote() {

        if (mNote != null) {
            MockNoteList.getInstance().delete(mNote);
        }

        finish();
    }


    private void saveNote() {

        String title = mTitle.getText().toString();
        String content = mContent.getText().toString();

        if (mNote == null) {
            Note note = new Note();
            note.setContent(content);
            note.setTitle(title);
            MockNoteList.getInstance().add(note);
        } else {
            mNote.setTitle(title);
            mNote.setContent(content);
            MockNoteList.getInstance().update(mNote);
        }


        finish();
    }

    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = (EditText) findViewById(R.id.et_title);
        mContent = (EditText) findViewById(R.id.et_content);
    }
}
