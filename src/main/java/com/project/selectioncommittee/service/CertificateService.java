package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public Certificate save(Certificate certificate) {
        return certificateRepository.save(certificate);
    }
}
