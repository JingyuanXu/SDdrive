package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Notes> getNotesByUser(Integer userId);

//    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
//    @Options(useGeneratedKeys = true, keyProperty = "noteId")
//    int createNotes(Notes note);

    @Insert("INSERT INTO notes ( notetitle,notedescription,userid) VALUES(#{noteTitle}, #{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNotes(Notes note);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Notes getNote(Integer noteId);

    @Delete("DELETE FROM notes WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);

    @Update("UPDATE notes SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    void updateNote(Notes note);
}
