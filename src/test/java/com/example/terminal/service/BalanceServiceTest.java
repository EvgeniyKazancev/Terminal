package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.enums.OperationType;
import com.example.terminal.enums.ResponseResult;
import com.example.terminal.repository.BalanceRepository;

import com.example.terminal.response.ResponseMessage;
import jakarta.persistence.EntityNotFoundException;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import net.minidev.json.annotate.JsonIgnore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {


    @Mock
    BalanceRepository balanceRepository;
    @Mock
    OperationService operationService;
    @Mock
    TransferService transferService;
    @InjectMocks
    private BalanceService balanceService;

    @BeforeEach
    public void setUp() {
        balanceService = new BalanceService(balanceRepository, operationService, null, null);
    }


    private Balance getTestBalance() {

        Balance balance = new Balance();
        balance.setId(1L);
        balance.setBalance(1000L);
        return balance;

    }

    @Test
    public void testGetBalance() {

        Balance actual = getTestBalance();

        when(balanceRepository.findByUserId(actual.getId())).thenReturn(Optional.of(actual));
        Balance expected = balanceService.getBalance(actual.getId());

        Assertions.assertEquals(actual, expected);

    }

    @Test
    public void testPutMoney() {

        Long summa = 100L;
        Balance bal = getTestBalance();
        Long balRes = bal.getBalance();
        when(balanceRepository.findByUserId(bal.getId())).thenReturn(Optional.of(bal));
        ResponseMessage expected = new ResponseMessage("Операция прошла успешно.", ResponseResult.SUCCESSFUL_OPERATION.getResult());
        ResponseMessage actual = balanceService.putMoney(bal.getId(),summa);
        System.out.println(actual.getMessage());
        System.out.println(expected.getMessage());;
        Assertions.assertEquals(expected.getMessage(),actual.getMessage());
        Assertions.assertEquals(balRes +summa,bal.getBalance());
    }

}