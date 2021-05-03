package com.project.selectioncommittee.repository;

import com.project.selectioncommittee.entity.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentStatusRepository extends JpaRepository<EnrollmentStatus, Integer> {
}
