package com.udacity.jwdnd.course1.cloudstorage.entity;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;

import java.util.List;

public interface NoteAbstract {
    public int addNote(Notes note);
    public List<Notes> getNotesByUser(Integer userId);
    public Notes getNote(Integer noteId);
    public void deleteNote(Integer noteId);
    public void updateNote(Notes note);

}
