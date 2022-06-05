package com.example.springapp1.service;

import com.example.springapp1.entity.UserEntity;
import com.example.springapp1.exception.UserAlreadyExistException;
import com.example.springapp1.exception.UserNotFoundException;
import com.example.springapp1.model.User;
import com.example.springapp1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
