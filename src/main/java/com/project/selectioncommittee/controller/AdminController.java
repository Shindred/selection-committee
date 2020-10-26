package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.Role;
import com.project.selectioncommittee.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user-list")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userRepository.findAllByRoles(Role.USER));
        return "user-list";
    }
}
