package com.example.springapp1.exception;

public class WalletAlreadyExistException extends Exception {

    public WalletAlreadyExistException(String message) {
        super(message);
    }
}
