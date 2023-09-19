package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.repository.BalanceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BalanceServiceTest {
    private BalanceService balanceService;
    @Mock
    private BalanceRepository balanceRepository;

//    @BeforeEach
//    public void setup() {
//        balanceService = new BalanceService(balanceRepository, Mockito.mock(OperationService.class), Mockito.mock(TransferService.class));
//    }
     @BeforeAll
     public  static  void before(){
         System.out.println("Start testing BalanceService.java");
     }
    @Test
    public void testGetBalance() {
         final Balance result = balanceService.getBalance(1L);
        Assertions.assertEquals(1200,result);
    }

    @Test
    void putMoney() {
//        Long id = 1L;
//        Long balance = 100L;
//        Balance bal = new Balance();
//        bal.setId(id);
//        bal.setBalance(balance);
//        Mockito.when(balanceRepository.findByUserId(id)).thenReturn(Optional.of(bal));
//        ResponseMessage re = balanceService
    }

    @Test
    void takeMoney() {
    }

    @Test
    void transferMoney() {
    }
}