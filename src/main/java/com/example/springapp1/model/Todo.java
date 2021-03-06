package com.example.springapp1.model;

import com.example.springapp1.entity.TodoEntity;

public class Todo {
    private Long id;
    private String title;
    private Boolean completed;

    public Todo() {
    }

    public static Todo toModel(TodoEntity entity) {
        Todo model = new Todo();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setCompleted(entity.isCompleted());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "\nTodo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
