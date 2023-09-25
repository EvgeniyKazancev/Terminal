package com.example.terminal.service;

 import com.example.terminal.entity.Balance;
import com.example.terminal.enums.ResponseResult;
import com.example.terminal.repository.BalanceRepository;
import com.example.terminal.response.ResponseMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
        balanceService = new BalanceService(balanceRepository, operationService, transferService, null);
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
        Long startBalance = bal.getBalance();
        when(balanceRepository.findByUserId(bal.getId())).thenReturn(Optional.of(bal));
        ResponseMessage expected = new ResponseMessage("Операция прошла успешно.", ResponseResult.SUCCESSFUL_OPERATION.getResult());
        ResponseMessage actual = balanceService.putMoney(bal.getId(), summa);
        Assertions.assertEquals(expected.getMessage(), actual.getMessage());
        Assertions.assertEquals(startBalance + summa, bal.getBalance());
    }

    @Test
    public void testTakeMoney() {

        Long summa = 100L;
        Balance bal = getTestBalance();
        Long startBalance = bal.getBalance();
        when(balanceRepository.findByUserId(bal.getId())).thenReturn(Optional.of(bal));
        ResponseMessage expected = new ResponseMessage("Операция прошла успешно.", ResponseResult.SUCCESSFUL_OPERATION.getResult());
        ResponseMessage actual = balanceService.takeMoney(bal.getId(), summa);
        Assertions.assertEquals(expected.getMessage(), actual.getMessage());
        Assertions.assertEquals(startBalance - summa, bal.getBalance());

    }
    @Test
    public void testTakeMoneyNotEnoughMoney() {

        Long summa = 1001L;
        Balance bal = getTestBalance();
        when(balanceRepository.findByUserId(bal.getId())).thenReturn(Optional.of(bal));
        ResponseMessage expected = new ResponseMessage("Недостаточно средств.", ResponseResult.ERROR_OPERATION.getResult());
        ResponseMessage actual = balanceService.takeMoney(bal.getId(), summa);
        Assertions.assertEquals(expected.getMessage(), actual.getMessage());
        Assertions.assertEquals(summa - 1, bal.getBalance());

    }
    @Test
    public  void  testTransferMoney(){
        Long summa = 100L;
        Balance senderTest = getTestBalance();
        Balance recipientTest = new Balance();
        recipientTest.setId(2L);
        recipientTest.setBalance(1000L);
        when(balanceRepository.findByUserId(senderTest.getId())).thenReturn(Optional.of(senderTest));
        when(balanceRepository.findByUserId(recipientTest.getId())).thenReturn(Optional.of(recipientTest));
        ResponseMessage expected = new ResponseMessage("Операция прошла успешно.",ResponseResult.SUCCESSFUL_OPERATION.getResult());
        ResponseMessage actual = balanceService.transferMoney(senderTest.getId(),recipientTest.getId(), summa);
        Assertions.assertEquals(expected.getMessage(),actual.getMessage());
        Assertions.assertEquals(900L,senderTest.getBalance() );
        Assertions.assertEquals(1100L,recipientTest.getBalance() );

    }
    @Test
    public  void  testTransferMoneyNotEnoughMoney(){
        Long summa = 1001L;
        Balance senderTest = getTestBalance();
        Balance recipientTest = new Balance();
        recipientTest.setId(2L);
        recipientTest.setBalance(1000L);
        when(balanceRepository.findByUserId(senderTest.getId())).thenReturn(Optional.of(senderTest));

        ResponseMessage expected = new ResponseMessage("Недостаточно средств.",ResponseResult.ERROR_OPERATION.getResult());
        ResponseMessage actual = balanceService.transferMoney(senderTest.getId(),recipientTest.getId(), summa);
        Assertions.assertEquals(expected.getMessage(),actual.getMessage());
        Assertions.assertEquals(1000L,senderTest.getBalance() );

    }
}