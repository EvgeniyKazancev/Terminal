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
import static org.mockito.Mockito.doNothing;
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


        when(usersRepository.save(any(Users.class))).thenReturn(user);
        when(balanceRepository.save(any(Balance.class))).thenReturn(balance);

        ResponseMessage response = userService.addUser(firstName, lastName);

        assertEquals("Новый пользователь добавлен", response.getMessage());
        assertEquals(1, response.getCode());
    }

    @Test
    public void deleteUser() {
        Users user = getTestUser();
        user.setId(1L);
        Balance balance = new Balance();
        balance.setBalance(0L);


        doNothing().when(usersRepository).deleteById(user.getId());
        doNothing().when(balanceRepository).deleteBalanceByUserId(user.getId());

        ResponseMessage expected = new ResponseMessage("Пользователь удален", ResponseResult.SUCCESSFUL_OPERATION.getResult());

        ResponseMessage actual = userService.deleteUser(user.getId());

        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getCode(), actual.getCode());
    }

    @Test
    public void updateUser() {
        Long userId = 1L;
        String firstName = "Ivan";
        String lastName = "Ivanov";

        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setId(userId);
        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));
        when(usersRepository.save(user)).thenReturn(user);


        ResponseMessage actual = userService.updateUser(user.getId(), "Aleksey", "Chernov");
        assertEquals("Пользователь изменен", actual.getMessage());
        assertEquals(ResponseResult.SUCCESSFUL_OPERATION.getResult(), actual.getCode());
    }
}