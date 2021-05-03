package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.service.CertificateService;
import com.project.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final CertificateService certificateService;

    @GetMapping
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String register(User user, Certificate certificate) {
        if (Optional.ofNullable(userService.loadUserByUsername(user.getUsername())).isPresent()) {
            return "registration";
        }
        userService.save(user, certificateService.save(certificate));
        return "redirect:/";
    }
}
