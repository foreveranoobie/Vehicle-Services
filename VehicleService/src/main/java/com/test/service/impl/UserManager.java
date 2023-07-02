package com.test.service.impl;

import com.test.entity.User;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManager implements UserDetailsManager {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(UserDetails user) {
        userRepository.save((User) user);
    }

    @Override
    @Transactional
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("User update is not supported");
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userRepository.findByUsername(username).ifPresent(userRepository::delete);
    }

    @Override
    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        userRepository.findByPassword(oldPassword).ifPresent(user -> {
            user.setPassword(newPassword);
        });
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElse(null);
    }
}
