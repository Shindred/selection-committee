package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.service.FacultyService;
import com.project.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
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


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final FacultyService facultyService;

    @GetMapping("/users")
    public String getAllUsers(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {
        model.addAttribute("usersPage", userService.loadUsers(pageable));
        model.addAttribute("url", "/admin/user-list");
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
        return "redirect:/faculties";
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
        return "redirect:/faculties";
    }

    @DeleteMapping("/faculty/{facultyId}")
    public String deleteFaculty(@PathVariable Integer facultyId) {
        facultyService.delete(facultyId);
        return "redirect:/faculties";
    }
}
