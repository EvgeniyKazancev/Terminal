//package com.example.terminal.controller;
//
//import com.example.terminal.entity.Operation;
//import com.example.terminal.entity.Users;
//import com.example.terminal.enums.OperationType;
//import com.example.terminal.repository.UsersRepository;
//import com.example.terminal.service.OperationService;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@SpringBootTest
//@AutoConfigureMockMvc
//class OperationControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//    @MockBean
//    private OperationService operationService;
//    @MockBean
//    private UsersRepository usersRepository;
//
//    @Test
//    void getOperationList() throws Exception {
//        Long userId = 1L;
//        LocalDate startDate = LocalDate.of(2023, 9, 1);
//        LocalDate finishDate = LocalDate.now();
//
//        Users user = new Users();
//        user.setId(userId);
//        user.setLastName("Ivan");
//        user.setFirstName("Kazancev");
//
//        Long operationId = 1L;
//        Operation operation = new Operation();
//        operation.setId(operationId);
//        operation.setUser(user);
//        operation.setOperationType(OperationType.PUT_MONEY);
//        operation.setDate(LocalDate.now());
//        operation.setSumma(100L);
//
//        List<Operation> list = new ArrayList<>();
//         list.add(operation);
//        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(operationService.getOperationList(userId, startDate, finishDate)).thenReturn(list);
//
//        mvc.perform(get("/operation/getList").param("userId", String.valueOf(userId)).param("startDate",String.valueOf(startDate)).param("finishDate",String.valueOf(finishDate)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(operationId))
//                .andExpect(jsonPath("$[0].user").value(user))
//                .andExpect(jsonPath("$[0].operationType").value(OperationType.PUT_MONEY.toString()))
//                .andExpect(jsonPath("$[0].summa").value(100L))
//                .andExpect(jsonPath("$[0].date").exists())
//                .andDo(print());
//
//    }
//}