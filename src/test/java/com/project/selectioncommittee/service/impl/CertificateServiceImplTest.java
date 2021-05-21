package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.repository.CertificateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CertificateServiceImplTest {

    @InjectMocks
    private CertificateServiceImpl certificateService;

    @Mock
    private CertificateRepository certificateRepositoryMock;

    @Mock
    private Certificate certificateMock;

    @Test
    public void saveTest() {
        certificateService.save(certificateMock);
        verify(certificateRepositoryMock).save(certificateMock);
    }
}