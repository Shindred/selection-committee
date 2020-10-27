package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.Role;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public Page<User> loadUsers(Pageable pageable) {
        return userRepository.findAllByRoles(Role.USER, pageable);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
