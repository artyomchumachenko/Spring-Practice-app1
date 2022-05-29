package com.example.acspring.service;

import com.example.acspring.entity.TodoEntity;
import com.example.acspring.entity.UserEntity;
import com.example.acspring.model.Todo;
import com.example.acspring.repository.TodoRepo;
import com.example.acspring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public Todo createTodo(TodoEntity todo, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo completeTodo(Long id) {
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.isCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }
}
