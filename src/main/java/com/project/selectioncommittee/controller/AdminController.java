package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userRepository) {
        this.userService = userRepository;
    }

    @GetMapping("/user-list")
    public String getAllUsers(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {
        model.addAttribute("userList", userService.loadUsers(pageable));
        model.addAttribute("url", "/admin/user-list");
        return "user-list";
    }
}
