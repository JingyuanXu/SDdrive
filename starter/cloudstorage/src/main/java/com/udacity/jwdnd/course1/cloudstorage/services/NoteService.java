package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.NoteAbstract;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public int addNote(Notes note) {
        return noteMapper.createNotes(note);
    }
    @Override
    public List<Notes> getNotesByUser(Integer userId) {
        return noteMapper.getNotesByUser(userId);
    }
    @Override
    public void updateNote(Notes note) {
        noteMapper.updateNote(note);

    }
    @Override
    public Notes getNote(Integer noteId) {
        return noteMapper.getNote(noteId);
    }
    @Override
    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }

}
