package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.FileAbstract;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    private FileAbstract fileAbstract;

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
            if(fileAbstract.getFile(file.getOriginalFilename()) != null){
                redirectAttributes.addFlashAttribute("errorMessage","The file exist. ");
                return "redirect:/home";
            }

            fileAbstract.addFile(file,userName);
            return "redirect:/home" ;
        }
        redirectAttributes.addFlashAttribute("errorMessage","No file");
        return "redirect:/home";

    }
    @GetMapping("/{name}")
    public String getFile(@PathVariable String name, Model model){

        model.addAttribute("file",fileAbstract.getFile(name));
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable int id, Model model){
        model.addAttribute("successMessage",fileAbstract.deleteFile(id));
        return "result";
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String name) throws Exception {
        try {
            Files file = fileAbstract.getFile(name);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(new ByteArrayResource(file.getFileData()));
        }
        catch(Exception e) {
            throw new Exception("Error downloading file");
        }
    }

}
