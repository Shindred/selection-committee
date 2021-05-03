package com.project.selectioncommittee.service.impl;

import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.repository.CertificateRepository;
import com.project.selectioncommittee.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    public Certificate save(Certificate certificate) {
        return certificateRepository.save(certificate);
    }
}
