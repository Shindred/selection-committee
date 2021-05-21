package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.entity.Role;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @Mock
    private User userMock;

    @Mock
    private Pageable pageableMock;

    @Mock
    private Certificate certificateMock;

    private int id = 1;

    @Test
    public void loadUserByUsernameTest() {
        String username = "u";
        userService.loadUserByUsername(username);
        verify(userRepositoryMock).findByUsername(username);
    }

    @Test
    public void findByIdTest() {
        when(userRepositoryMock.findById(id)).thenReturn(Optional.of(userMock));

        assertThat(userService.findById(id));

        verify(userRepositoryMock).findById(id);
    }

    @Test
    public void loadUsersTest() {
        userService.loadUsers(pageableMock);
        verify(userRepositoryMock).findAllByRoles(Role.USER, pageableMock);
    }

    @Test
    public void saveTest() {
        userService.save(userMock, certificateMock);

        verify(userMock).setCertificate(certificateMock);
        verify(userMock).setRoles(Collections.singleton(Role.USER));
        verify(passwordEncoderMock).encode(null);
        verify(userRepositoryMock).save(userMock);
    }

    @Test
    public void setBanStatusTest() {
        when(userRepositoryMock.getOne(id)).thenReturn(userMock);

        userService.setBanStatus(id, false);

        verify(userMock).setBanned(false);
        verify(userRepositoryMock).save(userMock);
    }
}