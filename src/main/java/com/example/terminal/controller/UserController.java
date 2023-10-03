package com.example.terminal.controller;


import com.example.terminal.entity.Users;
import com.example.terminal.response.ResponseMessage;
import com.example.terminal.service.BalanceService;
import com.example.terminal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BalanceService balanceService;
    @Autowired
    public UserController(UserService userService, BalanceService balanceService) {
        this.userService = userService;
        this.balanceService = balanceService;
    }
    @PutMapping("/addUser")
    public  ResponseMessage newUser(@RequestParam String firstName,@RequestParam String lastName){
        return userService.addUser(firstName,lastName);
    }
    @PutMapping("/deleteUser")
    public ResponseMessage deleteUser(@RequestParam Long userId){
       return   userService.deleteUser(userId);

    }


}
