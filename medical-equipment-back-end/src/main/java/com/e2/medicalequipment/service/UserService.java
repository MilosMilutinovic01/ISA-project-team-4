package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.JwtAuthenticationRequest;
import com.e2.medicalequipment.dto.UserTokenState;
import com.e2.medicalequipment.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public Optional<User> findByUsername(String username);
    public User changeUserStatus(User user);
    public UserTokenState login(JwtAuthenticationRequest loginDto);
}
