package com.example.acspring.repository;

import com.example.acspring.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<TodoEntity, Long> {
}
