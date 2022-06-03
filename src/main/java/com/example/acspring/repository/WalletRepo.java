package com.example.acspring.repository;

import com.example.acspring.entity.WalletEntity;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepo extends CrudRepository<WalletEntity, Long> {
}
