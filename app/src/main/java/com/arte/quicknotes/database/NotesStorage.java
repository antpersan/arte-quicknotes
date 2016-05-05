package com.arte.quicknotes.database;

import com.arte.quicknotes.models.Note;

import java.util.List;

/**
 * Created by antpersan on 28/4/16.
 */
public interface NotesStorage {

    Note get(int id);

    List<Note> getAll();

    void add(Note note);

    void delete(Note note);

    void update(Note note);

    void deleteAllNotes();

}
