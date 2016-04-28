package com.arte.quicknotes;

import com.arte.quicknotes.database.NotesStorage;
import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antpersan on 27/4/16.
 */
public class MockNoteList implements NotesStorage {

    private static List<Note> noteList;
    private static int nextId = 0;

    public static List<Note> getList() {
        if (noteList == null) {
            createList();
            //noteList = new ArrayList<>();
        }
        return noteList;
    }

    private static void createList() {
        noteList = new ArrayList<>();

        for (int i= 0; i < 5; i++) {
            Note note = new Note();
            note.setTitle("Nota #" + (i +1));
            note.setContent("Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator Lore impum generator ");
            noteList.add(note);
        }
    }

    @Override
    public Note get(int id) {
        for (Note note : getList()) {
            if (note.getId() == id) {
                return  note;
            }
        }
        return null;
    }

    @Override
    public List<Note> getAll() {
        return getList();
    }

    @Override
    public void add(Note note) {
        note.setId(nextId++);
        noteList.add(note);
    }

    public static void addNote(Note note) {
        note.setId(nextId++);
        noteList.add(note);
    }

    @Override
    public void delete(Note note) {
        noteList.remove(note);
    }

    @Override
    public void update(Note note) {

    }
}
