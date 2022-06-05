package com.example.springapp1.service;

import com.example.springapp1.entity.TodoEntity;
import com.example.springapp1.entity.UserEntity;
import com.example.springapp1.model.Todo;
import com.example.springapp1.repository.TodoRepo;
import com.example.springapp1.repository.UserRepo;
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
