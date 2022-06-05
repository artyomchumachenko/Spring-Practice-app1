package com.example.springapp1.exception;

public class WalletNotFoundException extends Exception {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
