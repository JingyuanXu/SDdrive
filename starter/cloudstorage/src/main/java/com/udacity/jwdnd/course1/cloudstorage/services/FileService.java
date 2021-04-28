package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }
    public String[] getFileByUser(Integer userId) {
        return fileMapper.getFileByUser(userId);
    }


    public void addFile(MultipartFile multipart, String userName) {
        try {
            String fileName = multipart.getOriginalFilename();
            String contentType = multipart.getContentType();
            String fileSize = String.valueOf(multipart.getSize());
            Integer userId = userMapper.getUserName(userName).getUserId();

            BufferedReader br;
            List<String> result = new ArrayList<>();
            String line;
            InputStream is = multipart.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            for (String element : result) {
                out.writeUTF(element);
            }
            byte[] fileData = baos.toByteArray();
            Files file = new Files(0, fileName, contentType, fileSize, userId, fileData);
            fileMapper.createFile(file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }

    public Files getFile(String fileName) {
        return fileMapper.getFileByName(fileName);
    }

    public void deleteFile(String fileName) {
        fileMapper.deleteFile(fileName);
    }

}
