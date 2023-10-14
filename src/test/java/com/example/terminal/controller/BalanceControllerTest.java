package com.example.terminal.controller;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Users;
import com.example.terminal.enums.ResponseResult;
import com.example.terminal.repository.UsersRepository;
import com.example.terminal.response.ResponseMessage;
import com.example.terminal.service.BalanceService;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BalanceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BalanceService balanceService;
    @MockBean
    private UsersRepository usersRepository;

    @Test
    public void getBalance() throws Exception {
      Long userId = 1L;

        String firstName = "Ivan";
        String lastName = "Ivanov";
        Users user = new Users();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        Balance balance = new Balance();
        balance.setId(1L);
        balance.setBalance(100L);

        when(balanceService.getBalance(1L)).thenReturn(balance);
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/balance/get")
                .param("userId", String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(100))

                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void putMoney() throws Exception {
     Long userId = 1L;
     Long summa = 100L;

        ResponseMessage rm = new ResponseMessage("Операция прошла успешно.", ResponseResult.SUCCESSFUL_OPERATION.getResult());
        when(balanceService.putMoney(anyLong(),anyLong())).thenReturn(rm);


        this.mockMvc.perform(put("/balance/put")
                .param("userId", String.valueOf(userId))
                .param("summa", String.valueOf(summa))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(rm.getMessage()))
                .andExpect(jsonPath("$.code").value(rm.getCode()))
                .andDo(print());

    }

    @Test
    public void takeMoney() throws Exception {
        Long userId = 1L;
        Long summa = 10L;

        ResponseMessage rm = new ResponseMessage("Операция прошла успешно", ResponseResult.SUCCESSFUL_OPERATION.getResult());
        when(balanceService.takeMoney(userId, summa)).thenReturn(rm);


        this.mockMvc.perform(put("/balance/take").param("userId", String.valueOf(userId)).param("summa", String.valueOf(summa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(rm.getMessage()))
                .andExpect(jsonPath("$.code").value(rm.getCode()))
                .andDo(print());


    }

    @Test
    void transferMoney() throws Exception {
        Long senderUserId = 1L;
        Long recipientUserId = 2L;
        Long summa = 10L;

        ResponseMessage rm = new ResponseMessage("Операция прошла успешно", ResponseResult.SUCCESSFUL_OPERATION.getResult());
        when(balanceService.transferMoney(senderUserId, recipientUserId, summa)).thenReturn(rm);


        this.mockMvc.perform(put("/balance/transfer").param("senderUserId", String.valueOf(senderUserId)).param("recipientUserId", String.valueOf(recipientUserId)).param("summa", String.valueOf(summa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(rm.getMessage()))
                .andExpect(jsonPath("$.code").value(rm.getCode()))
                .andDo(print());
    }
}





































