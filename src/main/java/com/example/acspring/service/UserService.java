package com.example.acspring.service;

import com.example.acspring.entity.UserEntity;
import com.example.acspring.exception.UserAlreadyExistException;
import com.example.acspring.exception.UserNotFoundException;
import com.example.acspring.model.User;
import com.example.acspring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            System.out.println(userRepo.findByUsername(user.getUsername()));
            throw new UserAlreadyExistException("User with this name was exist");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user;
        if (userRepo.findById(id).isPresent()) {
            user = userRepo.findById(id).get();
        } else {
            throw new UserNotFoundException("User is not found");
        }
        return User.toModel(user);
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }

    public String deleteUser(String username) throws UserNotFoundException {
        UserEntity deleteUser;
        if (userRepo.findByUsername(username) != null) {
            deleteUser = userRepo.findByUsername(username);
        } else {
            throw new UserNotFoundException("User with this name was not found");
        }
        userRepo.delete(deleteUser);
        return username;
    }
}
