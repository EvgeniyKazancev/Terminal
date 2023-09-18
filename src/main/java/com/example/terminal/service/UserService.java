package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Users;
import com.example.terminal.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BalanceService balanceService;
    private final UsersRepository usersRepository;

    public UserService(BalanceService balanceService, UsersRepository usersRepository) {
        this.balanceService = balanceService;
        this.usersRepository = usersRepository;
    }

    public Users findUser(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    public Users saveUser(Users user) {
        Balance balance = new Balance();
        balance.setBalance(0L);
        user = usersRepository.save(user);
        return user;
    }


}
