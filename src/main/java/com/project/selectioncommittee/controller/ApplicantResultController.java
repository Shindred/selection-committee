package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.service.ApplicantResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/applicant-result")
public class ApplicantResultController {

    private final ApplicantResultService applicantResultService;

    @PostMapping
    public String createApplicantResult(Integer facultyId, Integer userId) {
        applicantResultService.createApplicantResult(facultyId, userId);

        return "redirect:/faculty";
    }
}
