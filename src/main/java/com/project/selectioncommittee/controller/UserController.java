package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PreAuthorize("#userId == authentication.principal.id")
    @GetMapping("/{userId}")
    public String getProfile(@PathVariable Integer userId, Model model) {

        model.addAttribute("user", userService.findById(userId));

        return "profile";
    }
}
