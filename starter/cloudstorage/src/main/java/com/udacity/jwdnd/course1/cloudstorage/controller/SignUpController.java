package com.udacity.jwdnd.course1.cloudstorage.controller;



import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;


    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView(){
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, RedirectAttributes redirectAttributes){
        if(userService.isUserExist(user.getUsername())) {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Account have been created.");
            return "redirect:/login";
        }
        return null;
    }
}
