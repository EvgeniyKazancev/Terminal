package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Users;
import com.example.terminal.repository.BalanceRepository;


import com.example.terminal.response.ResponseMessage;
import com.example.terminal.enums.ResponseResult;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.terminal.enums.OperationType.PUT_MONEY;
import static com.example.terminal.enums.OperationType.TAKE_MONEY;


@Service
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final OperationService operationService;


    public BalanceService(BalanceRepository balanceRepository, @Lazy OperationService operationService) {
        this.balanceRepository = balanceRepository;
        this.operationService = operationService;
    }

    public Balance getBalance(Long userId) {
        Balance balance = balanceRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Пользователя с таким Id нет!"));
        return balance;
    }

    @Transactional
    public ResponseMessage putMoney(Long userId, Long summa) {
        Balance bal = getBalance(userId);
        bal.setBalance(bal.getBalance() + summa);
        operationService.addOperation(PUT_MONEY, summa, userId);
        ResponseMessage rm = new ResponseMessage("Операция прошла успешно.", ResponseResult.SUCCESSFUL_OPERATION.getResult());
        return rm;
    }

    @Transactional
    public ResponseMessage takeMoney(Long userId, Long summa) {

        Balance bal = getBalance(userId);
        if (bal.getBalance() < summa) {
            ResponseMessage rm = new ResponseMessage("Недостаточно средств.", ResponseResult.ERROR_OPERATION.getResult());
            return rm;
        } else
            bal.setBalance(bal.getBalance() - summa);
        operationService.addOperation(TAKE_MONEY, summa, userId);
        return new ResponseMessage("Операция прошла успешно", ResponseResult.SUCCESSFUL_OPERATION.getResult());

    }

}
