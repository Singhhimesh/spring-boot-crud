package com.bagisto.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bagisto.demo.services.UserServiceImp;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", this.userService.getLoggedInUser());

        return "category/index";
    }

    @GetMapping("create")
    public String create() {
        return "category/create";
    }
}
