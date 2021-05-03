package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.ExamResult;
import com.project.selectioncommittee.repository.ExamResultRepository;
import com.project.selectioncommittee.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;

    public void save(ExamResult examResult) {
        examResultRepository.save(examResult);
    }
}
