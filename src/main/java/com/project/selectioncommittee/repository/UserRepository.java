package com.project.selectioncommittee.repository;

import com.project.selectioncommittee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
