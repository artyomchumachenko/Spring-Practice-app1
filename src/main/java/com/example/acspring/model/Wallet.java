package com.example.acspring.model;

import com.example.acspring.entity.WalletEntity;

import java.math.BigDecimal;

public class Wallet {

    private Long id;
    private BigDecimal balance;

    public Wallet() {
    }

    public static Wallet toModel(WalletEntity entity) {
        Wallet model = new Wallet();
        model.setId(entity.getId());
        model.setBalance(entity.getBalance());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "\nWallet{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
