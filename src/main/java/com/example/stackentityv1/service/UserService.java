package com.example.stackentityv1.service;

import com.example.stackentityv1.model.User;
import com.example.stackentityv1.repository.UserRepo;
import com.example.stackentityv1.repository.UserRoleRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    UserRepo userRepo;
    UserRoleRepo roleRepo;

    public UserService(UserRepo userRepo, UserRoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional
    public void assignRoleToUser(int userId, int roleId) {
        roleRepo.findById(roleId).ifPresent(
                userRole -> {
                    userRepo.findById(userId).ifPresent(
                            user -> user.setUserRole(userRole)
                    );
                }
        );
    }

    public User save(User user) {
        return userRepo.save(user);
    }
}

