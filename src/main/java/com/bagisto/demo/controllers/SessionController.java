package com.bagisto.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SessionController {
    @GetMapping("login")
    public String login() {
        return "session/login";
    }
    
    @GetMapping("register")
    public String register() {
        return "session/register";
    }
}
