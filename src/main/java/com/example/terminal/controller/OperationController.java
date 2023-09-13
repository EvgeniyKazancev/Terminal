package com.example.terminal.controller;

import com.example.terminal.entity.Operation;
import com.example.terminal.service.OperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/getList")
    public List<Operation> getOperationList(@RequestParam Long userId, @RequestParam ZonedDateTime startDate , @RequestParam ZonedDateTime finishDate){
        return operationService.getOperationList(userId,startDate,finishDate);
    }
}
