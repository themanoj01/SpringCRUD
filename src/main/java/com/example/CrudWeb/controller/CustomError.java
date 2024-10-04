package com.example.CrudWeb.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomError implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        return "custom_error";
    }
}
