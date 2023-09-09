package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.repository.BalanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    private  final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }


    public Long getBalanceByUserId(Long userId) {

            Balance balance = balanceRepository.findByUserId(userId);
            if (balance != null){
                return balance.getBalance();
            }
            throw new EntityNotFoundException("Bank account nit found user ID" + userId);
    }
}
