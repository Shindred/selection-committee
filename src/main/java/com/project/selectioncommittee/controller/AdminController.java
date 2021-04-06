package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.service.FacultyService;
import com.project.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final FacultyService facultyService;

    @GetMapping("/user-list")
    public String getAllUsers(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {
        model.addAttribute("usersPage", userService.loadUsers(pageable));
        model.addAttribute("url", "/admin/user-list");
        return "user-list";
    }
    @PostMapping("/user-list")
    public String setUserBanStatus(Integer id, boolean isBanned){
        userService.setBanStatus(id, isBanned);
        return "redirect:/admin/user-list";
    }
    @GetMapping("/add-faculty")
    public String showAddFacultyPage() {
        return "add-faculty";
    }
    @PostMapping("/add-faculty")
    public String addFaculty(Faculty faculty) {
        facultyService.save(faculty);
        return "redirect:/faculty-list";
    }
    @GetMapping("/edit-faculty")
    public String showEditFacultyPage(Faculty faculty, Model model) {
        model.addAttribute("faculty", faculty);
        return "edit-faculty";
    }

    @PostMapping("/edit-faculty")
    public String editFaculty(Integer id, String name, Integer budgetPlacesAmount, Integer contractPlacesAmount) {
        Faculty faculty = new Faculty();
        if (!name.equals("")) {
            faculty.setName(name);
        }

        if (budgetPlacesAmount != null) {
            faculty.setBudgetPlacesAmount(budgetPlacesAmount);
        }

        if (contractPlacesAmount != null) {
            faculty.setContractPlacesAmount(contractPlacesAmount);
        }

        facultyService.edit(id, faculty);
        return "redirect:/faculty-list";
    }

    @GetMapping("/delete-faculty")
    public String deleteFaculty(Integer id) {
        facultyService.delete(id);
        return "redirect:/faculty-list";
    }
}
