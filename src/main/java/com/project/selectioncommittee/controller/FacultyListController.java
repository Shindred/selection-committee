package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.service.FacultyService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacultyListController {
    private final FacultyService facultyService;

    public FacultyListController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/faculty-list")
    public String viewFaculties(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {
        model.addAttribute("facultyList", facultyService.loadFaculties(pageable));
        model.addAttribute("url", "/faculty-list");
        return "faculty-list";
    }
}
