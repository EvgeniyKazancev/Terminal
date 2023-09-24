package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.repository.BalanceRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    BalanceServiceTest(BalanceRepository balanceRepository, OperationService operationService) {
        this.balanceRepository = balanceRepository;
        this.operationService = operationService;
    }

    @Test
    public void testGetBalance() {
        Balance actual = getTestBalance();
        System.out.println(actual);
        when(balanceRepository.findByUserId(anyLong())).thenReturn(Optional.of(actual));
        Balance expected = balanceService.getBalance(1L);
        System.out.println(expected);
        Assertions.assertEquals(actual, expected);

    }

    private Balance getTestBalance() {
        Balance balance = new Balance();
        balance.setId(1L);
        balance.setBalance(1000L);
        return balance;

    }


}