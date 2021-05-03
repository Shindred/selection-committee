package com.project.selectioncommittee.repository;

import com.project.selectioncommittee.entity.ApplicantResult;
import com.project.selectioncommittee.entity.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicantResultRepository extends JpaRepository<ApplicantResult, Integer> {
    Optional<ApplicantResult> findByFacultyIdAndUserId(Integer facultyId, Integer userId);

    Page<ApplicantResult> findAllByEnrollmentStatusId(Pageable pageable, Integer enrollmentStatusId);

    Page<ApplicantResult> findAllByFacultyId(Pageable pageable, Integer facultyId);

    List<ApplicantResult> findAllByFacultyOrderByTotalGradeDesc(Faculty faculty);
}
