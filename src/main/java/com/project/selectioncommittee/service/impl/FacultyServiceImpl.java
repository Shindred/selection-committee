package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.repository.FacultyRepository;
import com.project.selectioncommittee.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public Faculty findById(Integer facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(RuntimeException::new);
    }

    public Page<Faculty> loadFaculties(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }

    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty edit(Faculty faculty) {
        Faculty facultyToEdit = facultyRepository.getOne(faculty.getId());
        String facultyName = faculty.getName();
        int budgetPlaces = faculty.getBudgetPlacesAmount();
        int contractPlaces = faculty.getContractPlacesAmount();
        int passingGrade = faculty.getPassingGrade();

        if (facultyName != null && !facultyName.equals("")) {
            facultyToEdit.setName(facultyName);
        }
        if (budgetPlaces != 0) {
            facultyToEdit.setBudgetPlacesAmount(budgetPlaces);
        }
        if (contractPlaces != 0) {
            facultyToEdit.setContractPlacesAmount(contractPlaces);
        }
        if (passingGrade != 0) {
            facultyToEdit.setPassingGrade(passingGrade);
        }

        return facultyRepository.save(facultyToEdit);
    }

    public void delete(Integer id) {
        facultyRepository.deleteById(id);
    }
}
