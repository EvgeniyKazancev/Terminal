package com.example.terminal.repository;

import com.example.terminal.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BankAccountRepository implements JpaRepository<Balance, Long> {
    public Balance findByUserId(Long userId) {
        return findByUserId(userId);
    }


}
