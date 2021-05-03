package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findById(Integer userId);

    Page<User> loadUsers(Pageable pageable);

    User save(User user, Certificate certificate);

    void setBanStatus(Integer id, boolean isBanned);
}
