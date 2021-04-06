package com.project.selectioncommittee.service;

import com.project.selectioncommittee.entity.Certificate;
import com.project.selectioncommittee.entity.Role;
import com.project.selectioncommittee.entity.User;
import com.project.selectioncommittee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    public Page<User> loadUsers(Pageable pageable) {
        return userRepository.findAllByRoles(Role.USER, pageable);
    }

    public User save(User user, Certificate certificate) {
        user.setCertificate(certificate);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
