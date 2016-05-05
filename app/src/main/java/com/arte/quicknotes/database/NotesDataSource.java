package com.arte.quicknotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antpersan on 4/5/16.
 */
public class NotesDataSource implements NotesStorage {


    private SQLiteDatabase db;
    private NotesDbHelper dbHelper;
    private boolean dbEmpty = true;

    public NotesDataSource(Context context) {
        dbHelper = new NotesDbHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    @Override
    public void add(Note note) {
        ContentValues values = getContentValues(note.getTitle(), note.getContent());
        db.insert(NotesDbHelper.NoteEntry.TABLE_NAME, null, values);
    }

    public void update(Note note) {
        ContentValues values = getContentValues(note.getTitle(), note.getContent());
        String whereClause = NotesDbHelper.NoteEntry._ID + " = ?";
        String[] args = { String.valueOf(note.getId()) };
        db.update(NotesDbHelper.NoteEntry.TABLE_NAME, values, whereClause, args);
    }

    @Override
    public void delete(Note note) {
        long deleteId = note.getId();
        String[] args = { String.valueOf(deleteId) };
        db.delete(NotesDbHelper.NoteEntry.TABLE_NAME,
                NotesDbHelper.NoteEntry._ID + " = ?",
                args);
    }

    public void deleteAllNotes() {
        db.delete(NotesDbHelper.NoteEntry.TABLE_NAME, null, null);
    }

    public List<Note> getAll() {
        String[] projection = {
                NotesDbHelper.NoteEntry._ID,
                NotesDbHelper.NoteEntry.COLUMN_NAME_TITLE,
                NotesDbHelper.NoteEntry.COLUMN_NAME_CONTENT
        };
        String sortOrder =
                NotesDbHelper.NoteEntry._ID + " DESC";

        Cursor cursor = db.query(
                NotesDbHelper.NoteEntry.TABLE_NAME,     // The table to query
                projection,                             // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );

        List<Note> myNotes = new ArrayList<>();

        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getInt(cursor.getColumnIndex(NotesDbHelper.NoteEntry._ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(NotesDbHelper.NoteEntry.COLUMN_NAME_TITLE)));
            note.setContent(cursor.getString(cursor.getColumnIndex(NotesDbHelper.NoteEntry.COLUMN_NAME_CONTENT)));
            myNotes.add(note);
        }

        return myNotes;
    }

    private ContentValues getContentValues(String title, String content) {
        ContentValues values = new ContentValues();
        values.put(NotesDbHelper.NoteEntry.COLUMN_NAME_TITLE, title);
        values.put(NotesDbHelper.NoteEntry.COLUMN_NAME_CONTENT, content);
        return values;
    }

    @Override
    public Note get(int id) {
        return null;
    }

}
