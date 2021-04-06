package com.project.selectioncommittee.repository;

import com.project.selectioncommittee.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
}
