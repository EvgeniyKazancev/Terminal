package com.example.terminal.service;

import com.example.terminal.entity.Users;
import com.example.terminal.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


}
