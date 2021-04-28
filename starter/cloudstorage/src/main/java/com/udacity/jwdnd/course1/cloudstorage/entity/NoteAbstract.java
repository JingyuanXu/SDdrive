package com.udacity.jwdnd.course1.cloudstorage.entity;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;

public interface NoteAbstract {
    public void addNote(String title, String description, String userName);
    public Notes[] getNotesByUser(Integer userId);
    public Notes getNote(Integer noteId);
    public void deleteNote(Integer noteId);

}
