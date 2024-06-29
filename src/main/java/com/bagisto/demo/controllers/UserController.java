package com.bagisto.demo.controllers;

import com.bagisto.demo.respsitories.UserRepository;
import com.bagisto.demo.services.UserServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bagisto.demo.entities.User;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", this.userServiceImp.findAll());

        return "users/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());

        return "users/create";
    }

    @PostMapping("/store")
    public String store(
        @Valid @ModelAttribute("user") User user,
        BindingResult result,
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "users/create";
        }

        this.userRepository.save(user);

        redirectAttributes.addFlashAttribute("message", "User created successfully.");

        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) {
            return "redirect:/users";
        }

        model.addAttribute("user", user.get());

        return "users/edit";
    }

    @PostMapping("/update/{id}")
    public String update(
        @PathVariable Integer id,
        @Valid @ModelAttribute("user") User user,
        BindingResult result,
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "users/edit";
        }

        user.setId(id);

        this.userRepository.save(user);

        redirectAttributes.addFlashAttribute("message", "User updated successfully.");

        return "redirect:/users";
    }
}
