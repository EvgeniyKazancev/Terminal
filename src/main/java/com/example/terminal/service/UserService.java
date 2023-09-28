package com.example.terminal.service;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Users;
import com.example.terminal.repository.UsersRepository;
import jakarta.persistence.*;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    @PersistenceContext
    private EntityManager entityManager;

   // EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.baeldung.movie_catalog");

    public UserService(UsersRepository usersRepository) {

        this.usersRepository = usersRepository;

    }

    public Users findUser(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }


    public Users saveUser(String firstName, String lastName) {

        entityManager.getTransaction().begin();
        Users user = new Users();
        Balance balance = new Balance();
        balance.setBalance(0L);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        return user;
    }

    public void deleteUser(Long userId) {
        usersRepository.findById(userId);

    }

    public Users updateUser(Long userId, String firstname, String lastname) {
        Users user = findUser(userId);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        return user;
    }


}

