package com.example.springapp1.controller;

import com.example.springapp1.exception.UserNotFoundException;
import com.example.springapp1.exception.WalletAlreadyExistException;
import com.example.springapp1.exception.WalletNotFoundException;
import com.example.springapp1.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/new")
    public ResponseEntity newWallet(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(walletService.newWallet(userId));
        } catch (WalletAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/addFundsByWalletId")
    public ResponseEntity addFundsByWalletId(@RequestParam Long walletId, @RequestParam BigDecimal amount) {
        try {
            return ResponseEntity.ok(walletService.addFundsByWalletId(walletId, amount));
        } catch (WalletNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/addFundsByUserId")
    public ResponseEntity addFundsByUserId(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        try {
            return ResponseEntity.ok(walletService.addFundsByUserId(userId, amount));
        } catch (UserNotFoundException | WalletNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteWalletById(@RequestParam Long walletId) {
        try {
            return ResponseEntity.ok(walletService.deleteWalletById(walletId));
        } catch (WalletNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
