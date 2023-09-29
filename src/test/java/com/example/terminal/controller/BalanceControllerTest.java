package com.example.terminal.controller;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Users;
import com.example.terminal.enums.ResponseResult;
import com.example.terminal.response.ResponseMessage;
import com.example.terminal.service.BalanceService;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BalanceController.class)
class BalanceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BalanceService balanceService;


    @Test
    public void getBalance() throws Exception {
        Long userId =1L;
        Balance balance = new Balance();
        Users user = new Users();
        user.setFirstName("Ivan");
        user.setLastName("Kazancev");
        balance.setBalance(100L);

        when(balanceService.getBalance(userId)).thenReturn(balance);

        String expected = "{\"balance\": 100,\"user\":{\"firstName\":\"Ivan\",\"lastName\":\"Kazancev\",\"id\":1},\"id\":1}";
        this.mockMvc.perform(get("/balance/get").param(String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expected));
    }

    @Test
    public void putMoney() throws Exception {
        Long userId =1L;
        Long summa = 10L;

        ResponseMessage rm = new ResponseMessage("Операция прошла успешно",ResponseResult.SUCCESSFUL_OPERATION.getResult());
        when(balanceService.putMoney(userId,summa)).thenReturn(rm);

        String expected = "{\"message\":\"Операция прошла успешно.\",\"code\":1}";
        this.mockMvc.perform(put("/balance/put").param(String.valueOf(userId)).param(String.valueOf(summa)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expected));

    }

    @Test
   public void takeMoney() throws Exception {
        Long userId =1L;
        Long summa = 10L;

        ResponseMessage rm = new ResponseMessage("Операция прошла успешно",ResponseResult.SUCCESSFUL_OPERATION.getResult());
        when(balanceService.takeMoney(userId,summa)).thenReturn(rm);

        String expected = "{\"message\":\"Операция прошла успешно.\",\"code\":1}";
        this.mockMvc.perform(put("/balance/put").param(String.valueOf(userId)).param(String.valueOf(summa)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expected));
    }
    @Test
    void transferMoney() throws Exception{
        Long senderUserId =1L;
        Long recipientUserId = 2L;
        Long summa = 10L;

        ResponseMessage rm = new ResponseMessage("Операция прошла успешно",ResponseResult.SUCCESSFUL_OPERATION.getResult());
        when(balanceService.transferMoney(senderUserId,recipientUserId,summa)).thenReturn(rm);

        String expected = "{\"message\":\"Операция прошла успешно.\",\"code\":1}";
        this.mockMvc.perform(put("/balance/put").param(String.valueOf(senderUserId)).param(String.valueOf(recipientUserId)).param(String.valueOf(summa)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expected));
    }
}





































