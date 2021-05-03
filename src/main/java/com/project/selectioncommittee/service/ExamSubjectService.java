package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.ExamSubject;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.repository.ExamSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExamSubjectService {

    private final ExamSubjectRepository examSubjectRepository;

    public ExamSubject findById(Integer id) {
        return examSubjectRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public Set<ExamSubject> getExamSubjectsThatUserDoesNotHave(User user) {
        List<ExamSubject> allExamSubjects = examSubjectRepository.findAll();
        user.getExamResults().forEach(
                examResult -> allExamSubjects.remove(examResult.getExamResultId().getExamSubject())
        );

        return new HashSet<>(allExamSubjects);
    }
}
