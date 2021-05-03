package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacultyService {

    Faculty findById(Integer facultyId);

    Page<Faculty> loadFaculties(Pageable pageable);

    Faculty save(Faculty faculty);

    Faculty edit(Faculty faculty);

    void delete(Integer id);
}
