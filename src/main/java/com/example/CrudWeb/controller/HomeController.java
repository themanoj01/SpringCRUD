package com.example.CrudWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

//    @GetMapping
//    public String home() {
//        return "home";
//    }
//    @GetMapping("/landing")
//    public String landing() {
//        return "landing";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String register(Model model) {
//        return "register";
//    }

//    @PostMapping("/register")
//    public String registerUser(User user, Model model) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.getRoles().add("USER");
//        userRepository.save(user);
//        return "redirect:/login";
//    }


}
