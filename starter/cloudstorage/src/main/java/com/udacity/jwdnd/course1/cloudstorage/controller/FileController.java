package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;


    @PostMapping
    public String addFile(@RequestParam(value = "fileUpload") MultipartFile file,
                          RedirectAttributes redirectAttributes) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userService.getUserByName(currentPrincipalName);
        String userName = user.getUsername();
        if(!file.isEmpty() &&  user != null){
            if(file.getSize() > 10000000){
                redirectAttributes.addFlashAttribute("errorMessage","The file size is over 10000000");
                return "redirect:/home";
            }
            if(fileService.getFile(file.getOriginalFilename()) != null){
                redirectAttributes.addFlashAttribute("errorMessage","The file exist. ");
                return "redirect:/home";
            }

            fileService.addFile(file,userName);
            return "redirect:/home" ;
        }
        redirectAttributes.addFlashAttribute("errorMessage","No file");
        return "redirect:/home";

    }
    @GetMapping("/{name}")
    public String getFile(@PathVariable String name, Model model){

        model.addAttribute("file",fileService.getFile(name));
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable int id, Model model){
        model.addAttribute("successMessage",fileService.deleteFile(id));
        return "result";
    }

}
