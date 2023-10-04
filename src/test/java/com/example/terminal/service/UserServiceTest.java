package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Users;
import com.example.terminal.enums.ResponseResult;
import com.example.terminal.repository.BalanceRepository;
import com.example.terminal.repository.UsersRepository;
import com.example.terminal.response.ResponseMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private BalanceRepository balanceRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(balanceRepository, usersRepository);
    }

    public Users getTestUser() {
        Users user = new Users();
        user.setId(1L);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        return user;
    }

    @Test
    public void testFindUser() {
        Users userActual = getTestUser();
        when(usersRepository.findById(userActual.getId())).thenReturn(Optional.of(userActual));

        Users expectedUser = userService.findUser(userActual.getId());

        assertEquals(userActual, expectedUser);

    }

    @Test
    public void testAddUser() {
        String firstName = "Ivan";
        String lastName = "Ivanov";

        Users user = new Users();
        user.setId(1L);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        Balance balance = new Balance();
        balance.setBalance(0L);
        balance.setUser(user);

        when(usersRepository.save(any(Users.class))).thenReturn(user);
        when(balanceRepository.save(any(Balance.class))).thenReturn(balance);

        ResponseMessage response = userService.addUser(firstName, lastName);

        assertEquals("Новый пользователь добавлен", response.getMessage());
        assertEquals(1,response.getCode());
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
    }
}