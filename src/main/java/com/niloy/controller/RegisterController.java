package com.niloy.controller;

import com.niloy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }
}
