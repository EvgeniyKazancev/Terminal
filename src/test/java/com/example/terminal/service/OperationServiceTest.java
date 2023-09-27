package com.example.terminal.service;

import com.example.terminal.entity.Operation;
import com.example.terminal.entity.Users;
import com.example.terminal.enums.OperationType;
import com.example.terminal.repository.OperationRepository;
import com.example.terminal.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {
    @Mock
    OperationRepository operationRepository;
    @Mock
    UsersRepository usersRepository;
    @InjectMocks
    OperationService operationService;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        operationService = new OperationService(operationRepository,usersRepository);
    }
    @Test
   public void getOperationList() {
        Long id = 1L;
        LocalDate sd = LocalDate.of(2023,7,30);
        LocalDate fd = LocalDate.now();
        List<Operation> expectedList = Collections.singletonList(new Operation());
        when(operationRepository.findAllByUserIdAndAndDateBetween(eq(id),eq(sd),eq(fd))).thenReturn(expectedList);

        List<Operation> actualList = operationService.getOperationList(id,sd,fd);
        assertEquals(expectedList,actualList);
     }
    @Test
    public void getOperationList_WithEmptyResult() {
        Long id = 1L;
        LocalDate sd = LocalDate.of(2023,7,30);
        LocalDate fd = LocalDate.now();
        when(operationRepository.findAllByUserIdAndAndDateBetween(eq(id),eq(sd),eq(fd))).thenReturn(Collections.emptyList());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,()-> {operationService.getOperationList(id,sd,fd);
        });
        String expectedMessage = "Данные в указанный период отсутствуют" ;
        assertEquals(expectedMessage,exception.getMessage());
     }
    @Test
    public void addOperation() {
        Long id = 1L;
        Long summa = 100L;
        Users user = new Users();
        user.setId(id);
        when(usersRepository.findById (eq(id))).thenReturn(Optional.of(user));

        Operation expectedOperation = new Operation();
        when(operationRepository.save(any(Operation.class))).thenReturn(expectedOperation);

        Operation actualOperation = operationService.addOperation(OperationType.PUT_MONEY,summa,id);
        assertEquals(expectedOperation,actualOperation);
    }
}