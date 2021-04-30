package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialAbstract;
import com.udacity.jwdnd.course1.cloudstorage.entity.FileAbstract;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private FileAbstract fileAbstract;
    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialAbstract credentialAbstract;
    @Autowired
    private EncryptionService encryptionService;

    public HomeController(FileAbstract fileAbstract, UserService userService, NoteService noteService, CredentialAbstract credentialAbstract, EncryptionService encryptionService) {
        this.fileAbstract = fileAbstract;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialAbstract = credentialAbstract;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String homeView(Model model, RedirectAttributes redirectAttributes, Authentication authentication){
        String username = authentication.getName();
        User user = userService.getUserByName(username);
        if(user == null || username == null ){
            redirectAttributes.addFlashAttribute("errorMessage","Please login");
            return "redirect:/login";
        }else {
            int userId = user.getUserId();
            model.addAttribute("fileslist", fileAbstract.getFileListByUser(userId));
            model.addAttribute("noteslist", noteService.getNotesByUser(userId));
            model.addAttribute("credentialslist", credentialAbstract.getCredentialByUserId(userId));
            model.addAttribute("encryptionService", encryptionService);
            return "home";
        }
    }

}
