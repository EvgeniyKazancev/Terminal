package com.example.terminal.controller;

import com.example.terminal.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminal/account")
public class BankAccountController {
  private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/{userId}/balance")
  public ResponseEntity<Double> getBalance(@PathVariable ("userId") Long userId){
        Double balance = bankAccountService.getBalanceByUserId(userId);
        return ResponseEntity.ok(balance);
  }
}
