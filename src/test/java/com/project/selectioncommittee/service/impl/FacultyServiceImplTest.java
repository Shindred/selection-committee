package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.Faculty;
import com.project.selectioncommittee.repository.FacultyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FacultyServiceImplTest {

    @InjectMocks
    private FacultyServiceImpl facultyService;

    @Mock
    private FacultyRepository facultyRepositoryMock;

    @Mock
    private Faculty facultyMock;

    @Mock
    private Pageable pageableMock;

    private int id = 1;

    @Before
    public void setUp() {
        when(facultyRepositoryMock.findById(id)).thenReturn(Optional.of(facultyMock));
    }

    @Test
    public void findByIdTest() {
        assertThat(facultyService.findById(id)).isNotNull();
        verify(facultyRepositoryMock).findById(id);
    }

    @Test
    public void loadFacultiesTest() {
        facultyService.loadFaculties(pageableMock);

        verify(facultyRepositoryMock).findAll(pageableMock);
    }

    @Test
    public void saveTest() {
        facultyService.save(facultyMock);
        verify(facultyRepositoryMock).save(facultyMock);
    }

    @Test
    public void editTest() {
        when(facultyRepositoryMock.getOne(id)).thenReturn(facultyMock);

        Faculty faculty = new Faculty();
        faculty.setId(id);

        facultyService.edit(faculty);

        verify(facultyRepositoryMock).save(facultyMock);
    }

    @Test
    public void deleteTest() {
        facultyService.delete(id);
        verify(facultyRepositoryMock).deleteById(id);
    }
}