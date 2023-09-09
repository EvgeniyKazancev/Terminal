package com.example.terminal.controller;

import com.example.terminal.dto.BalanceDTO;
import com.example.terminal.entity.Balance;
import com.example.terminal.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService bankAccountService) {
        this.balanceService = bankAccountService;
    }

    //    @GetMapping("/{userId}/balance")
//  public ResponseEntity<Double> getBalance(@PathVariable ("userId") Long userId){
//        Double balance = balanceService.getBalanceByUserId(userId);
//        return ResponseEntity.ok(balance);
//  }
    @GetMapping("/get")

    public BalanceDTO getBalance( @RequestParam Long userId)  {
       Long balance = balanceService.getBalanceByUserId(userId);
        return getBalance(balance);
    }

}
