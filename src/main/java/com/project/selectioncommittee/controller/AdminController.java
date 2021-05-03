package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.ApplicantResult;
import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.service.ApplicantResultService;
import com.project.selectioncommittee.service.FacultyService;
import com.project.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final FacultyService facultyService;
    private final ApplicantResultService applicantResultService;

    @GetMapping("/users")
    public String getAllUsers(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {
        Page<User> usersPage = userService.loadUsers(pageable);

        model.addAttribute("usersPage", usersPage);
        model.addAttribute("pageNumbers", getPageNumbers(usersPage));
        return "user-list";
    }

    @PostMapping("/users")
    public String setUserBanStatus(Integer id, boolean isBanned) {
        userService.setBanStatus(id, isBanned);
        return "redirect:/admin/users";
    }

    @GetMapping("/faculty")
    public String showAddFacultyPage() {
        return "add-faculty";
    }

    @PostMapping("/faculty")
    public String addFaculty(Faculty faculty) {
        facultyService.save(faculty);
        return "redirect:/faculty";
    }

    @GetMapping("/faculty/{facultyId}")
    public String showEditFacultyPage(@PathVariable Integer facultyId, Model model) {
        model.addAttribute("faculty", facultyService.findById(facultyId));
        return "edit-faculty";
    }

    @PatchMapping("/faculty/{facultyId}")
    public String editFaculty(@PathVariable Integer facultyId, String name, Integer budgetPlacesAmount, Integer contractPlacesAmount) {
        Faculty faculty = new Faculty();

        faculty.setId(facultyId);
        faculty.setName(name);
        if (budgetPlacesAmount != null) {
            faculty.setBudgetPlacesAmount(budgetPlacesAmount);
        }
        if (contractPlacesAmount != null) {
            faculty.setContractPlacesAmount(contractPlacesAmount);
        }

        facultyService.edit(faculty);
        return "redirect:/faculty";
    }

    @DeleteMapping("/faculty/{facultyId}")
    public String deleteFaculty(@PathVariable Integer facultyId) {
        facultyService.delete(facultyId);
        return "redirect:/faculty";
    }

    @GetMapping("/applicant-result/by/enstatus/{enrollmentStatusId}")
    public String getApplicantResults(@PathVariable Integer enrollmentStatusId, Pageable pageable, Model model) {
        Page<ApplicantResult> applicantResultPage = applicantResultService.findAllByEnrollmentStatusId(
                pageable, enrollmentStatusId
        );

        model.addAttribute("applicantResultPage", applicantResultPage);
        model.addAttribute("pageNumbers", getPageNumbers(applicantResultPage));

        return "applicant-results";
    }

    @GetMapping("/applicant-result/by/faculty/{facultyId}")
    public String getApplicantResultsByFaculty(@PathVariable Integer facultyId, Pageable pageable, Model model) {

        Faculty faculty = facultyService.findById(facultyId);

        Page<ApplicantResult> applicantResultPage = applicantResultService.findAllByFacultyId(pageable, facultyId);

        model.addAttribute("faculty", faculty);
        model.addAttribute("applicantResultPage", applicantResultPage);
        model.addAttribute("pageNumbers", getPageNumbers(applicantResultPage));

        return "applicant-results";
    }

    @PostMapping("/applicant-result/faculty/{facultyId}")
    public String finalizeTheStatement(@PathVariable Integer facultyId) {

        applicantResultService.finalizeTheStatement(facultyId);

        return "redirect:/admin/applicant-result/by/faculty/" + facultyId;
    }

    private List<Integer> getPageNumbers(Page<?> page) {
        return IntStream.rangeClosed(1, page.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
    }
}
