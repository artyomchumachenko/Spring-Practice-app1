package com.example.acspring.repository;

import com.example.acspring.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
//    void deleteByUsername(String username);

}
