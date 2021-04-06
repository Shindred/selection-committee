package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public Page<Faculty> loadFaculties(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }

    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty edit(Integer idToEdit, Faculty faculty) {
        Faculty facultyToEdit = facultyRepository.getOne(idToEdit);
        if (faculty.getName() != null && !faculty.getName().equals("")) {
            facultyToEdit.setName(faculty.getName());
        }
        if (faculty.getBudgetPlacesAmount() != 0) {
            facultyToEdit.setBudgetPlacesAmount(faculty.getBudgetPlacesAmount());
        }
        if (faculty.getContractPlacesAmount() != 0) {
            facultyToEdit.setContractPlacesAmount(faculty.getContractPlacesAmount());
        }
        if (faculty.getPassingGrade() != 0) {
            facultyToEdit.setPassingGrade(faculty.getPassingGrade());
        }
        return facultyRepository.save(facultyToEdit);
    }

    public void delete(Integer id) {
        facultyRepository.deleteById(id);
    }
}
