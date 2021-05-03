package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.ApplicantResult;
import com.project.selectioncommittee.entity.ExamSubject;
import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.repository.ApplicantResultRepository;
import com.project.selectioncommittee.service.FacultyService;
import com.project.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;
    private final ApplicantResultRepository applicantResultRepository;
    private final UserService userService;

    @Transactional
    @GetMapping
    public String viewFaculties(Authentication authentication, @PageableDefault Pageable pageable, Model model) {

        User user = (User) authentication.getPrincipal();

        User userFromDb = userService.findById(user.getId());

        Page<Faculty> facultiesPage = facultyService.loadFaculties(pageable);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, facultiesPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());

        List<Faculty> facultiesUserRegisteredFor = facultiesPage.getContent().stream()
                .filter(f -> f.getApplicantResults()
                        .contains(
                                applicantResultRepository.findByFacultyIdAndUserId(f.getId(), user.getId())
                                        .orElse(new ApplicantResult())
                        )
                ).collect(Collectors.toList());

        Set<ExamSubject> userExamSubjects = userFromDb.getExamResults()
                .stream()
                .map(er -> er.getExamResultId().getExamSubject())
                .collect(Collectors.toSet());

        model.addAttribute("facultiesUserRegisteredFor", facultiesUserRegisteredFor);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("facultiesPage", facultiesPage);
        model.addAttribute("userExamSubjects", userExamSubjects);

        return "faculty-list";
    }
}
