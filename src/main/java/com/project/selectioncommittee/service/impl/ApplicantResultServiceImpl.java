package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.ApplicantResult;
import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.entity.EnrollmentStatus;
import com.project.selectioncommittee.entity.ExamResult;
import com.project.selectioncommittee.entity.ExamSubject;
import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.repository.ApplicantResultRepository;
import com.project.selectioncommittee.repository.EnrollmentStatusRepository;
import com.project.selectioncommittee.repository.FacultyRepository;
import com.project.selectioncommittee.service.ApplicantResultService;
import com.project.selectioncommittee.service.FacultyService;
import com.project.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicantResultServiceImpl implements ApplicantResultService {

    private static final int REGISTERED_ENROLLMENT_STATUS_ID = 1;
    private static final int NOT_PASSED_ENROLLMENT_STATUS_ID = 2;
    private static final int PASSED_ON_BUDGET_ENROLLMENT_STATUS_ID = 3;
    private static final int PASSED_ON_CONTRACT_ENROLLMENT_STATUS_ID = 4;

    private final ApplicantResultRepository applicantResultRepository;
    private final EnrollmentStatusRepository enrollmentStatusRepository;
    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;
    private final UserService userService;

    @Transactional
    @Override
    public void createApplicantResult(Integer facultyId, Integer userId) {

        if (applicantResultRepository.findByFacultyIdAndUserId(facultyId, userId).isPresent()) {
            throw new RuntimeException();
        }

        ApplicantResult applicantResult = new ApplicantResult();
        User user = userService.findById(userId);
        Faculty faculty = facultyService.findById(facultyId);
        Set<ExamSubject> userExamSubjects = user.getExamResults()
                .stream()
                .map(er -> er.getExamResultId().getExamSubject())
                .collect(Collectors.toSet());


        if (!userExamSubjects.containsAll(faculty.getExamSubjects())) {
            throw new RuntimeException();
        }

        applicantResult.setEnrollmentStatus(enrollmentStatusRepository.findById(REGISTERED_ENROLLMENT_STATUS_ID)
                .orElseThrow(RuntimeException::new));
        applicantResult.setFaculty(faculty);
        applicantResult.setUser(user);

        Certificate certificate = user.getCertificate();

        int totalCertificateGrade = certificate.getGeographyGrade() + certificate.getEnglishLanguageGrade() + certificate.getHistoryGrade()
                + certificate.getMathematicsGrade() + certificate.getPhysicsGrade() + certificate.getUkrainianLanguageGrade();

        int totalExamGrade = user.getExamResults().stream()
                .filter(er -> faculty.getExamSubjects().contains(er.getExamResultId().getExamSubject()))
                .map(ExamResult::getGrade)
                .reduce(0, Integer::sum);

        applicantResult.setTotalGrade(totalCertificateGrade + totalExamGrade);

        applicantResultRepository.save(applicantResult);
    }

    @Override
    public Page<ApplicantResult> findAllByEnrollmentStatusId(Pageable pageable, Integer enrollmentStatusId) {
        return applicantResultRepository.findAllByEnrollmentStatusId(pageable, enrollmentStatusId);
    }

    @Override
    public Page<ApplicantResult> findAllByFacultyId(Pageable pageable, Integer facultyId) {
        return applicantResultRepository.findAllByFacultyId(pageable, facultyId);
    }

    @Transactional
    @Override
    public void finalizeTheStatement(Integer facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(RuntimeException::new);

        int budgetPlacesAmount = faculty.getBudgetPlacesAmount();
        int contractPlacesAmount = faculty.getContractPlacesAmount();
        List<ApplicantResult> statement = applicantResultRepository
                .findAllByFacultyOrderByTotalGradeDesc(faculty);
        EnrollmentStatus passedOnBudgetStatus = enrollmentStatusRepository.findById(PASSED_ON_BUDGET_ENROLLMENT_STATUS_ID)
                .orElseThrow(RuntimeException::new);
        EnrollmentStatus passedOnContractStatus = enrollmentStatusRepository.findById(PASSED_ON_CONTRACT_ENROLLMENT_STATUS_ID)
                .orElseThrow(RuntimeException::new);
        EnrollmentStatus notPassedStatus = enrollmentStatusRepository.findById(NOT_PASSED_ENROLLMENT_STATUS_ID)
                .orElseThrow(RuntimeException::new);

        for (ApplicantResult appResult : statement) {
            boolean isTotalGradeMoreThanOrEqualsPassingGrade = appResult.getTotalGrade() >= faculty.getPassingGrade();

            if (budgetPlacesAmount > 0 && isTotalGradeMoreThanOrEqualsPassingGrade) {
                appResult.setEnrollmentStatus(passedOnBudgetStatus);
                budgetPlacesAmount--;
            } else if (contractPlacesAmount > 0 && isTotalGradeMoreThanOrEqualsPassingGrade) {
                appResult.setEnrollmentStatus(passedOnContractStatus);
                contractPlacesAmount--;
            } else {
                appResult.setEnrollmentStatus(notPassedStatus);
            }
        }

        applicantResultRepository.saveAll(statement);

        faculty.setStatementIsFinalized(true);
        facultyRepository.save(faculty);
    }
}
