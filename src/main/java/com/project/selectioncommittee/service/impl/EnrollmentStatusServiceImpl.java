package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.EnrollmentStatus;
import com.project.selectioncommittee.repository.EnrollmentStatusRepository;
import com.project.selectioncommittee.service.EnrollmentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentStatusServiceImpl implements EnrollmentStatusService {

    private final EnrollmentStatusRepository enrollmentStatusRepository;

    @Override
    public EnrollmentStatus findById(Integer id) {
        return enrollmentStatusRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
