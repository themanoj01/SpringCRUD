package com.example.CrudWeb.auth.controller;

import com.example.CrudWeb.auth.model.User;
import com.example.CrudWeb.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/home")
    public String home() {
        return "landing";
    }

    @GetMapping("/login")
    public String showLogin(String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        try {
            authService.registerUser(user);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("email")) {
                result.rejectValue("email", null, e.getMessage());
            } else if (e.getMessage().contains("username")) {
                result.rejectValue("username", null, e.getMessage());
            }
            return "register";
        }
        return "redirect:/auth/login";
    }
}
