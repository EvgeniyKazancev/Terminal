package com.example.terminal.controller;


import com.example.terminal.entity.Users;
import com.example.terminal.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PutMapping("/add")
//    public Users addUser(@RequestParam String firstName,@RequestParam String lastName ){
//        return userService.addUser(firstName,lastName);
//    }

}
