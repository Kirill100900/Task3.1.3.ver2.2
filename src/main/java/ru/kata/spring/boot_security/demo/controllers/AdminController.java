package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping("/admin")
    public String adminPage(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "adminPage";
    }

    @PostMapping("/admin/new")
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.registerNewAccount(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit/{id}")
    public String editUser(@ModelAttribute("editUser") User user) {
        userService.editAccount(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
