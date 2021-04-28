package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.NoteAbstract;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService implements NoteAbstract {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotesMapper noteMapper;

    public NoteService(UserMapper userMapper, NotesMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }
    public void addNote(String title, String description, String userName) {
        Integer userId = userMapper.getUserByName(userName).getUserId();
        Notes note = new Notes(0, title, description, userId);
        noteMapper.createNotes(note);
    }

    public Notes[] getNotesByUser(Integer userId) {
        return noteMapper.getNotesByUser(userId);
    }

    public Notes getNote(Integer noteId) {
        return noteMapper.getNote(noteId);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }

}
