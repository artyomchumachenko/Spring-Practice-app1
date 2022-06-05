package com.example.springapp1.repository;

import com.example.springapp1.entity.WalletEntity;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepo extends CrudRepository<WalletEntity, Long> {
}
