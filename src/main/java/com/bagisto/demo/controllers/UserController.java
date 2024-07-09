package com.bagisto.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bagisto.demo.entities.User;
import com.bagisto.demo.respsitories.UserRepository;
import com.bagisto.demo.services.UserServiceImp;

import jakarta.validation.Valid;


@Controller
@RequestMapping("admin/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImp userServiceImp;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

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

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);

        redirectAttributes.addFlashAttribute("message", "User created successfully.");

        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) {
            return "redirect:/admin/users";
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

        return "redirect:/admin/users";
    }

    @PostMapping("/destroy/{id}")
    public String destroy(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "User is deleted successfully.");

        this.userServiceImp.deleteById(id);

        return "redirect:/admin/users";
    }

    @PostMapping("/mass-destroy")
    public String massDestroy(RedirectAttributes redirectAttributes) {
        this.userRepository.deleteAll();

        redirectAttributes.addFlashAttribute("message", "All users deleted successfully.");

        return "redirect:/admin/users";
    }
}
