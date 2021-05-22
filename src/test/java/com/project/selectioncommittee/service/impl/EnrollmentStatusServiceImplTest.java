package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.EnrollmentStatus;
import com.project.selectioncommittee.repository.EnrollmentStatusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnrollmentStatusServiceImplTest {

    @InjectMocks
    private EnrollmentStatusServiceImpl enrollmentStatusService;

    @Mock
    private EnrollmentStatusRepository enrollmentStatusRepositoryMock;

    @Mock
    private EnrollmentStatus enrollmentStatusMock;

    @Test
    public void findByIdTest() {
        int id = 1;
        when(enrollmentStatusRepositoryMock.findById(id)).thenReturn(Optional.of(enrollmentStatusMock));
        assertThat(enrollmentStatusService.findById(id)).isNotNull();
        verify(enrollmentStatusRepositoryMock).findById(id);
    }
}