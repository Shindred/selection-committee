package com.project.selectioncommittee.controller;

import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faculty-list")
public class FacultyListController {

    private final FacultyService facultyService;

    @GetMapping
    public String viewFaculties(@PageableDefault Pageable pageable, Model model) {
        Page<Faculty> facultiesPage = facultyService.loadFaculties(pageable);
        model.addAttribute("facultiesPage", facultiesPage);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, facultiesPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        return "faculty-list";
    }
}
