package com.example.terminal.controller;

import com.example.terminal.entity.Operation;
import com.example.terminal.entity.Users;
import com.example.terminal.enums.OperationType;
import com.example.terminal.service.OperationService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OperationController.class)
class OperationControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private OperationService operationService;

    @Test
    void getOperationList() throws Exception {
        Long userId = 1L;
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate finishDate = LocalDate.now();

        Users user = new Users();
        user.setLastName("Ivan");
        user.setFirstName("Kazancev");

        Operation operation = new Operation();
        operation.setId(userId);
        operation.setUser(user);
        operation.setOperationType(OperationType.PUT_MONEY);
        operation.setDate(LocalDate.now());
        operation.setSumma(100L);

        List<Operation> list = new ArrayList<>();

        when(operationService.getOperationList(userId, startDate, finishDate)).thenReturn(list);
        String expected = "{\"id\": 4,\n" +
                "       \"user\": {\n" +
                "            \"firstName\": \"Ivan\",\n" +
                "            \"lastName\": \"Kazancev\",\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        \"operationType\": \"PUT_MONEY\",\n" +
                "        \"summa\": 100,\n" +
                "        \"date\": \"2023-09-29\"}";

        this.mvc.perform(get("/operation/getList").param(String.valueOf(userId)).param(String.valueOf(startDate)).param(String.valueOf(finishDate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expected));
    }
}