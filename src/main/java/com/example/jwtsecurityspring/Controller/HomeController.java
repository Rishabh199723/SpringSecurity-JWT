package com.example.jwtsecurityspring.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to home";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to dashboard";
    }
}
