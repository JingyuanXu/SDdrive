package com.udacity.jwdnd.course1.cloudstorage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notes {
    @JsonProperty("noteid")
    private Integer noteId;
    @JsonProperty("notetitle")
    private String noteTitle;
    @JsonProperty("notedescription")
    private String noteDescription;
    @JsonProperty("userid")
    private Integer userId;

    public Notes(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }


    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
