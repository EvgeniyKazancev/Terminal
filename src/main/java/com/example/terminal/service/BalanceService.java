package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.repository.BalanceRepository;


import com.example.terminal.response.ResponseMessage;
import com.example.terminal.response.ResponseResult;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
public class BalanceService  {
    private  final BalanceRepository balanceRepository;


    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;

    }


//    public Long getBalanceByUserId(Long userId) {
//
//            Balance balance = balanceRepository.findByUserId(userId);
//            if (balance != null){
//                return balance.getBalance();
//            }
//            throw new EntityNotFoundException("Bank account nit found user ID" + userId);
//    }


    public Balance getBalance(Long userId) {
        Balance balance = balanceRepository.findByUserId(userId).orElseThrow(()-> new EntityNotFoundException("Пользователя с таким Id нет!"));
            return balance;
    }

    @Transactional
    public ResponseMessage putMoney(Long userId, Long summa) {
        Balance bal = getBalance(userId);
         bal.setBalance(bal.getBalance() + summa);
         ResponseMessage rm = new ResponseMessage("",ResponseResult.SUCCESSFUL_OPERATION.getResult());
         return rm;
    }
    @Transactional
    public ResponseMessage takeMoney(Long userId,Long summa){
        Balance bal = getBalance(userId);
        if(bal.getBalance() <  summa){
            ResponseMessage rm = new ResponseMessage("Недостаточно средств",ResponseResult.ERROR_OPERATION.getResult());
            return rm;
        }else
            bal.setBalance(bal.getBalance() - summa);
            return new ResponseMessage("",ResponseResult.SUCCESSFUL_OPERATION.getResult());

    }
}
