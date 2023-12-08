package com.e2.medicalequipment.service;


import com.e2.medicalequipment.model.Role;

import java.util.List;

public interface RoleService {
	Role findById(Long id);
	List<Role> findByName(String name);
}
