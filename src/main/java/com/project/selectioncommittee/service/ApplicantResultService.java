package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.ApplicantResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicantResultService {

    void createApplicantResult(Integer facultyId, Integer userId);

    Page<ApplicantResult> findAllByEnrollmentStatusId(Pageable pageable, Integer enrollmentStatusId);

    Page<ApplicantResult> findAllByFacultyId(Pageable pageable, Integer facultyId);

    void finalizeTheStatement(Integer facultyId);
}
