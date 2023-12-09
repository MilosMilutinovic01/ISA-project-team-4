package com.e2.medicalequipment.service;


import com.e2.medicalequipment.dto.UserRequest;
import com.e2.medicalequipment.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
}
