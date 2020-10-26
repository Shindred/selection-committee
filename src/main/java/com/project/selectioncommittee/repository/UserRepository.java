package com.project.selectioncommittee.repository;

import com.project.selectioncommittee.entity.Role;
import com.project.selectioncommittee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findAllByRoles(Role role);
}
