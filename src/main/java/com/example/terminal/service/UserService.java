package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Users;
import com.example.terminal.repository.BalanceRepository;
import com.example.terminal.repository.UsersRepository;
import jakarta.persistence.*;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BalanceRepository balanceRepository;
    private final UsersRepository usersRepository;
    @PersistenceContext
    private EntityManager entityManager;

    // EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.baeldung.movie_catalog");

    public UserService(BalanceRepository balanceRepository, UsersRepository usersRepository) {
        this.balanceRepository = balanceRepository;

        this.usersRepository = usersRepository;

    }

    public Users findUser(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }


    public Users addUser(String firstName, String lastName) {
        Users user = new Users();
        Balance balance = new Balance();
        balance.setBalance(0L);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        usersRepository.save(user);
        balance.setUser(user);
        balanceRepository.save(balance);
        return user;
    }

    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
        balanceRepository.deleteBalanceByUserId(userId);
    }

    public Users updateUser(Long userId, String firstname, String lastname) {
        Users user = findUser(userId);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        usersRepository.save(user);
        return user;
    }


}

