package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.ExamResult;
import com.project.selectioncommittee.entity.ExamResultId;
import com.project.selectioncommittee.entity.ExamSubject;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.service.ExamResultService;
import com.project.selectioncommittee.service.ExamSubjectService;
import com.project.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ExamSubjectService examSubjectService;
    private final ExamResultService examResultService;

    @PreAuthorize("#userId == authentication.principal.id")
    @GetMapping("/{userId}")
    public String getProfile(@PathVariable Integer userId, Model model) {
        User user = userService.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("newExamSubjects", examSubjectService.getExamSubjectsThatUserDoesNotHave(user));

        return "profile";
    }

    @PostMapping("/result")
    public String addNewExamResult(ExamResult examResult, Integer userId, Integer examSubjectId) {
        User user = userService.findById(userId);
        ExamResultId examResultId = new ExamResultId(user, examSubjectService.findById(examSubjectId));
        examResult.setExamResultId(examResultId);
        examResultService.save(examResult);
        return "redirect:/user/" + user.getId();
    }
}
