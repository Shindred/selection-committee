package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.repository.FacultyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Page<Faculty> loadFaculties(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }
}
