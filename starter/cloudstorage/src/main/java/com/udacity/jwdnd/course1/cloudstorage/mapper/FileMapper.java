package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    Files getFileByName(String fileName);

    @Select("SELECT filename FROM FILES WHERE userid = #{userId}")
    String[] getFileByUser(Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int createFile(Files file);

    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    void deleteFile(String fileName);
}
