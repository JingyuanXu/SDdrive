package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.NoteAbstract;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NoteAbstract noteAbstract;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Notes> getNotes(int userId){
        return noteAbstract.getNotesByUser(userId);
    }

    @PostMapping
    public String addNote(Authentication authentication, Notes note, RedirectAttributes redirectAttributes){
        String username= authentication.getName();
        User user = userService.getUserByName(username);

        if(note.getNoteId().intValue() > 0){
            try{
                noteAbstract.updateNote(note);
                redirectAttributes.addFlashAttribute("successMessage", "Updated successfully.");
                return "redirect:/home";
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("errorMessage", e);
                return "redirect:/home";
            }
        }else {
            try{
                note.setUserId(user.getUserId());
                noteAbstract.addNote(note);
                redirectAttributes.addFlashAttribute("successMessage", "Added one note");
                return "redirect:/home";
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("errorMessage", e);
                return "redirect:/home";
            }

        }
    }
    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable int noteId, RedirectAttributes redirectAttributes) {
        try {
            noteAbstract.deleteNote(noteId);
            redirectAttributes.addFlashAttribute("successMessage", "Deleted one note");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e);
            return "redirect:/home";
        }
    }
}