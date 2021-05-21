package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.ApplicantResult;
import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.entity.EnrollmentStatus;
import com.project.selectioncommittee.entity.ExamResult;
import com.project.selectioncommittee.entity.ExamResultId;
import com.project.selectioncommittee.entity.ExamSubject;
import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.repository.ApplicantResultRepository;
import com.project.selectioncommittee.repository.EnrollmentStatusRepository;
import com.project.selectioncommittee.repository.FacultyRepository;
import com.project.selectioncommittee.service.FacultyService;
import com.project.selectioncommittee.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ApplicantResultServiceImplTest {

    @InjectMocks
    private ApplicantResultServiceImpl applicantResultService;

    @Mock
    private ApplicantResultRepository applicantResultRepositoryMock;

    @Mock
    private EnrollmentStatusRepository enrollmentStatusRepositoryMock;

    @Mock
    private FacultyService facultyServiceMock;

    @Mock
    private FacultyRepository facultyRepositoryMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private User userMock;

    @Mock
    private Faculty facultyMock;

    @Mock
    private ExamResult examResultMock;

    @Mock
    private ExamResultId examResultIdMock;

    @Mock
    private ExamSubject examSubjectMock;

    @Mock
    private EnrollmentStatus enrollmentStatusMock;

    @Mock
    private Certificate certificateMock;

    @Mock
    private Pageable pageableMock;

    @Mock
    private EnrollmentStatus passedOnBudgetEnrollmentStatusMock;

    @Mock
    private EnrollmentStatus passedOnContractStatusMock;

    @Mock
    private EnrollmentStatus notPassedStatusMock;

    @Mock
    private ApplicantResult applicantResultMock1;

    @Mock
    private ApplicantResult applicantResultMock2;

    @Mock
    private ApplicantResult applicantResultMock3;

    @Mock
    private ApplicantResult applicantResultMock4;

    @Mock
    private ApplicantResult applicantResultMock5;

    private final int id = 1;

    @Before
    public void setUp() {
        when(userServiceMock.findById(id)).thenReturn(userMock);
        when(facultyServiceMock.findById(id)).thenReturn(facultyMock);
        when(userMock.getExamResults()).thenReturn(Collections.singleton(examResultMock));
        when(examResultMock.getExamResultId()).thenReturn(examResultIdMock);
        when(examResultIdMock.getExamSubject()).thenReturn(examSubjectMock);
    }

    @Test
    public void createApplicantResultTest() {
        when(enrollmentStatusRepositoryMock.findById(anyInt())).thenReturn(Optional.of(enrollmentStatusMock));
        when(userMock.getCertificate()).thenReturn(certificateMock);

        applicantResultService.createApplicantResult(id, id);

        verify(applicantResultRepositoryMock).save(any(ApplicantResult.class));
    }

    @Test
    public void createApplicantResultTest_subjectNotMatchCase() {
        when(facultyMock.getExamSubjects()).thenReturn(Collections.singleton(new ExamSubject()));

        assertThrows(
                RuntimeException.class,
                () -> applicantResultService.createApplicantResult(id, id)
        );
    }

    @Test
    public void createApplicantResultTest_applicantResultAlreadyPresentCcase() {
        when(applicantResultRepositoryMock.findByFacultyIdAndUserId(id, id))
                .thenReturn(Optional.of(applicantResultMock1));

        assertThrows(
                RuntimeException.class,
                () -> applicantResultService.createApplicantResult(id, id)
        );
    }

    @Test
    public void findAllByEnrollmentStatusId() {
        applicantResultService.findAllByEnrollmentStatusId(pageableMock, id);
        verify(applicantResultRepositoryMock).findAllByEnrollmentStatusId(pageableMock, id);
    }

    @Test
    public void findAllByFacultyId() {
        applicantResultService.findAllByFacultyId(pageableMock, id);
        verify(applicantResultRepositoryMock).findAllByFacultyId(pageableMock, id);
    }

    @Test
    public void finalizeTheStatement() {
        when(facultyRepositoryMock.findById(id)).thenReturn(Optional.of(facultyMock));
        when(enrollmentStatusRepositoryMock.findById(3)).thenReturn(Optional.of(passedOnBudgetEnrollmentStatusMock));
        when(enrollmentStatusRepositoryMock.findById(4)).thenReturn(Optional.of(passedOnContractStatusMock));
        when(enrollmentStatusRepositoryMock.findById(2)).thenReturn(Optional.of(notPassedStatusMock));
        when(facultyMock.getContractPlacesAmount()).thenReturn(3);
        when(facultyMock.getBudgetPlacesAmount()).thenReturn(1);
        when(applicantResultRepositoryMock.findAllByFacultyOrderByTotalGradeDesc(facultyMock)).thenReturn(
                Arrays.asList(applicantResultMock1, applicantResultMock2, applicantResultMock3, applicantResultMock4, applicantResultMock5)
        );
        when(facultyMock.getPassingGrade()).thenReturn(4);
        when(applicantResultMock1.getTotalGrade()).thenReturn(10);
        when(applicantResultMock2.getTotalGrade()).thenReturn(9);
        when(applicantResultMock3.getTotalGrade()).thenReturn(8);
        when(applicantResultMock4.getTotalGrade()).thenReturn(5);
        when(applicantResultMock5.getTotalGrade()).thenReturn(1);


        applicantResultService.finalizeTheStatement(id);

        verify(applicantResultMock1).setEnrollmentStatus(passedOnBudgetEnrollmentStatusMock);
        verify(applicantResultMock2).setEnrollmentStatus(passedOnContractStatusMock);
        verify(applicantResultMock3).setEnrollmentStatus(passedOnContractStatusMock);
        verify(applicantResultMock4).setEnrollmentStatus(passedOnContractStatusMock);
        verify(applicantResultMock5).setEnrollmentStatus(notPassedStatusMock);
        verify(facultyRepositoryMock).save(facultyMock);
    }
}