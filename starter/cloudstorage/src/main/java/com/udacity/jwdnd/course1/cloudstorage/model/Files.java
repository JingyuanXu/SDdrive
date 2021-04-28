package com.udacity.jwdnd.course1.cloudstorage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Files {
    @JsonProperty("fileId")
    private int fileId;
    @JsonProperty("filename")
    private String fileName;
    @JsonProperty("contenttype")
    private String contentType;
    @JsonProperty("filesize")
    private String fileSize;
    @JsonProperty("userid")
    private Integer userId;
    @JsonProperty("filedata")
    private byte[] fileData;

    public Files(int fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

}
