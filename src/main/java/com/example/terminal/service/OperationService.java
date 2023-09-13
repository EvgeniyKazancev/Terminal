package com.example.terminal.service;

import com.example.terminal.entity.Operation;
import com.example.terminal.enums.OperationType;
import com.example.terminal.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OperationService {
   private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<Operation> getOperationList(Long userId, ZonedDateTime startDate, ZonedDateTime finishDate){
        List<Operation> operationList = operationRepository.findOperationByDateBetween(userId,startDate,finishDate);
        return  operationList;
    }
    public Operation addOperation(OperationType type, Long summa , Long userId){
         Operation operation = new Operation();
         operation.setOperationType(type);
         operation.setSumma(summa);
         operation.setId(userId);
         return operationRepository.save(operation);
    }
}
