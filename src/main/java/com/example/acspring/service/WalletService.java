package com.example.acspring.service;

import com.example.acspring.entity.UserEntity;
import com.example.acspring.entity.WalletEntity;
import com.example.acspring.exception.UserAlreadyExistException;
import com.example.acspring.exception.UserNotFoundException;
import com.example.acspring.exception.WalletAlreadyExistException;
import com.example.acspring.exception.WalletNotFoundException;
import com.example.acspring.model.Wallet;
import com.example.acspring.repository.UserRepo;
import com.example.acspring.repository.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private UserRepo userRepo;

    public Wallet newWallet(Long userId) throws WalletAlreadyExistException {
        if (walletRepo.findById(userId).isPresent()) {
            throw new WalletAlreadyExistException("This wallet already exist");
        }
        System.out.println("userId (newWallet) = " + userId);
        WalletEntity wallet = new WalletEntity();
        UserEntity user = userRepo.findById(userId).get();
        wallet.setUser(user);
        wallet.setBalance(BigDecimal.ZERO);
        return Wallet.toModel(walletRepo.save(wallet));
    }

    public BigDecimal addFundsByWalletId(Long walletId, BigDecimal amount) throws WalletNotFoundException {
        if (walletRepo.findById(walletId).isEmpty()) {
            throw new WalletNotFoundException("Wallet not found");
        }
        WalletEntity wallet = walletRepo.findById(walletId).get();
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepo.save(wallet);
        return wallet.getBalance();
    }

    public BigDecimal addFundsByUserId(Long userId, BigDecimal amount) throws WalletNotFoundException, UserNotFoundException {
        if (userRepo.findById(userId).isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        UserEntity user = userRepo.findById(userId).get();
        if (user.getWallet() == null) {
            throw new WalletNotFoundException("Wallet not found");
        }
        WalletEntity wallet = walletRepo.findById(user.getWallet().getId()).get();
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepo.save(wallet);
        return wallet.getBalance();
    }

    public String deleteWalletById(Long walletId) throws WalletNotFoundException {
        if (walletRepo.findById(walletId).isEmpty()) {
            throw new WalletNotFoundException("Wallet not found");
        }
        walletRepo.deleteById(walletId);
        return "Wallet success removed";
    }
}
