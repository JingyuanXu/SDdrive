package com.udacity.jwdnd.course1.cloudstorage.entity;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileAbstract {
    public List<Files> getFileListByUser(Integer userId);
    public void addFile(MultipartFile multipart, String userName);
    public Files getFile(String fileName);
    public void deleteFile(String fileName);
}
