package com.example.terminal.controller;

import com.example.terminal.entity.Operation;
import com.example.terminal.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;
    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/getList")
   @io.swagger.v3.oas.annotations.Operation(description = "Получение списка опреаций за заданный промежуток времени по Id")
    public List<Operation> getOperationList(@RequestParam Long userId, @RequestParam(required = false) LocalDate startDate , @RequestParam(required = false) LocalDate finishDate){
        return operationService.getOperationList(userId,startDate,finishDate);
    }
}
