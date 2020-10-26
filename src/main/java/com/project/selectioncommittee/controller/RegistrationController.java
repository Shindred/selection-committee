package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.Role;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUserToDb(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return "registration";
        }

        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/";
    }
}
