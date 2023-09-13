package com.example.terminal.controller;

import com.example.terminal.entity.Balance;
import com.example.terminal.response.ResponseMessage;
import com.example.terminal.service.BalanceService;
import com.example.terminal.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService){
      this.balanceService = balanceService;

    }
    @GetMapping("/get")

    public Balance getBalance(@RequestParam Long userId)  {
        return balanceService.getBalance(userId);
    }
    @PutMapping("/put")
    public ResponseMessage putMoney(@RequestParam Long userId,@RequestParam Long summa){
        return balanceService.putMoney(userId,summa);
    }
    @PutMapping("/take")
    public ResponseMessage takeMoney(@RequestParam Long userId, @RequestParam Long summa){
        return balanceService.takeMoney(userId,summa);
    }


}
