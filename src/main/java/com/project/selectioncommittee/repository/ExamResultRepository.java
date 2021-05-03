package com.project.selectioncommittee.repository;

import com.project.selectioncommittee.entity.ExamResult;
import com.project.selectioncommittee.entity.ExamResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult, ExamResultId> {
}
