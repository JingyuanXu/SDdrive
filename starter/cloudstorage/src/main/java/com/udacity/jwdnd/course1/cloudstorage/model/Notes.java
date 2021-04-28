package com.udacity.jwdnd.course1.cloudstorage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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



}
