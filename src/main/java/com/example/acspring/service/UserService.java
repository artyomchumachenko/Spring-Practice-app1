package com.example.acspring.service;

import com.example.acspring.entity.UserEntity;
import com.example.acspring.exception.UserAlreadyExistException;
import com.example.acspring.exception.UserNotFoundException;
import com.example.acspring.model.User;
import com.example.acspring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this name was exist");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User is not found");
        }
        return User.toModel(user);
    }
}
