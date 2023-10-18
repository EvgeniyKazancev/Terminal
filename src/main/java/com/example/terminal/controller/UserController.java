package com.example.terminal.controller;


import com.example.terminal.response.ResponseMessage;
import com.example.terminal.service.BalanceService;
import com.example.terminal.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }
    @PutMapping("/addUser")
    @Operation(description = "Добавление нового пользователя")
    public  ResponseMessage newUser(@RequestParam String firstName,@RequestParam String lastName){
        return userService.addUser(firstName,lastName);
    }
    @PutMapping("/deleteUser")
    @Operation(description = "Удаление пользователя")
    public ResponseMessage deleteUser(@RequestParam Long userId){
       return   userService.deleteUser(userId);

    }
    @PutMapping("/updateUser")
    @Operation(description = "Изменение пользователя")
    public ResponseMessage updateUser(@RequestParam Long userId, @RequestParam String firstName, @RequestParam String lastName ){
        return userService.updateUser(userId,firstName,lastName);
    }


}
