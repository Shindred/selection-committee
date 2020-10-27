package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.Role;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String addUserToDb(User user) {
        if (Optional.ofNullable(userService.loadUserByUsername(user.getUsername())).isPresent()) {
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);
        return "redirect:/";
    }
}
