package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.repository.BankAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private  final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    public Double getBalanceByUserId(Long userId) {

            Balance balance = bankAccountRepository.findByUserId(userId);
            if (balance != null){
                return balance.getBalance();
            }
            throw new EntityNotFoundException("Bank account nit found user ID" + userId);
    }
}
