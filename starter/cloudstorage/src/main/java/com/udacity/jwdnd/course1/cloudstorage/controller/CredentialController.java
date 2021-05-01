package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialAbstract;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
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

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    private UserService userService;
    @Autowired
    private CredentialAbstract credentialAbstract;
    @Autowired
    private EncryptionService encryptionService;

    @PostMapping
    public String editCredential(Credential credential, Authentication authentication, RedirectAttributes redirectAttributes) {

        String secretKey = generateSecretKey();
        String encryptPassword = encryptionService.encryptValue(credential.getPassword(), secretKey);
        credential.setKey(secretKey);
        credential.setPassword(encryptPassword);

        if(credential.getCredentialId().intValue() > 0){
            try {
                credentialAbstract.updateCredential(credential);
                redirectAttributes.addFlashAttribute("successMessage", "Updated successfully.");
                return "redirect:/home";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e);
                return "redirect:/home";
            }
        }else{
            try {
                String username = authentication.getName();
                int userId = userService.getUserByName(username).getUserId();
                credential.setUserid(userId);
                credentialAbstract.addCredential(credential);
                redirectAttributes.addFlashAttribute("successMessage", "Added one credential");
                return "redirect:/home";
            }  catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e);
                return "redirect:/home";
            }
        }
    }
    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId, RedirectAttributes redirectAttributes) {
        try {
            credentialAbstract.deleteCredential(credentialId);
            redirectAttributes.addFlashAttribute("successMessage", "Your credentials were deleted successful.");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e);
            return "redirect:/home";
        }
    }
    private String generateSecretKey() {
        try{
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[16];
            random.nextBytes(key);
            return Base64.getEncoder().encodeToString(key);
        } catch (Exception e) {
        }
        return null;
    }
}