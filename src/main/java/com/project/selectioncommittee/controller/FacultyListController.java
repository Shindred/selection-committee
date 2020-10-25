package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.repository.FacultyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacultyListController {
    private final FacultyRepository facultyRepository;

    public FacultyListController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @GetMapping("/faculty-list")
    public String viewFaculties(Model model) {
        model.addAttribute("facultyList", facultyRepository.findAll());
        return "faculty-list";
    }
}
