package com.example.stackentityv1.service;

import com.example.stackentityv1.model.UserRole;
import com.example.stackentityv1.repository.UserRoleRepo;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private final UserRoleRepo userRoleRepo;

    public UserRoleService(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    public UserRole save(UserRole role){
        return userRoleRepo.save(role);
    }
}
