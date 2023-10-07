package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Users;
import com.example.terminal.enums.ResponseResult;
import com.example.terminal.repository.BalanceRepository;
import com.example.terminal.repository.UsersRepository;
import com.example.terminal.response.ResponseMessage;
import jakarta.persistence.*;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BalanceRepository balanceRepository;
    private final UsersRepository usersRepository;

    public UserService(BalanceRepository balanceRepository, UsersRepository usersRepository) {
        this.balanceRepository = balanceRepository;

        this.usersRepository = usersRepository;

    }

    public Users findUser(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }


    public ResponseMessage addUser(String firstName, String lastName) {
        Users user = new Users();
        Balance balance = new Balance();
        balance.setBalance(0L);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        usersRepository.save(user);

        balanceRepository.save(balance);
        return new ResponseMessage("Новый пользователь добавлен", ResponseResult.SUCCESSFUL_OPERATION.getResult());
    }

    public ResponseMessage deleteUser(Long userId) {
        usersRepository.deleteById(userId);
        balanceRepository.deleteBalanceByUserId(userId);
        return new ResponseMessage("Пользователь удален", ResponseResult.SUCCESSFUL_OPERATION.getResult());
    }

    public ResponseMessage updateUser(Long userId, String firstname, String lastname) {
        Users user = findUser(userId);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        usersRepository.save(user);
        return new ResponseMessage("Пользователь изменен",ResponseResult.SUCCESSFUL_OPERATION.getResult() );
    }


}

