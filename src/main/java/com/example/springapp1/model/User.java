package com.example.springapp1.model;

import com.example.springapp1.entity.UserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Long id;
    private String username;
    private List<Todo> todos;
    private BigDecimal walletBalance;

    public User() {
    }

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        //Massive entity convert to massive models(stream api use)
        model.setTodos(entity.getTodos().stream().map(Todo::toModel).collect(Collectors.toList()));
        if (model.isWalletExist(entity)) {
            model.setWalletBalance(entity.getWallet().getBalance());
        }
        return model;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(BigDecimal walletBalance) {
        this.walletBalance = walletBalance;
    }

    /**
     * Check wallet on exist
     * @param user UserEntity for us model
     * @return wallet is existed
     */
    private boolean isWalletExist(UserEntity user) {
        return user.getWallet() != null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", todos=" + todos +
                ", walletBalance=" + walletBalance +
                '}';
    }
}
