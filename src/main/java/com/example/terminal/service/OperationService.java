package com.example.terminal.service;

import com.example.terminal.entity.Operation;
import com.example.terminal.entity.Users;
import com.example.terminal.enums.OperationType;
import com.example.terminal.repository.OperationRepository;
import com.example.terminal.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final UsersRepository usersRepository;
    public OperationService(OperationRepository operationRepository, UsersRepository usersRepository) {
        this.operationRepository = operationRepository;
        this.usersRepository = usersRepository;
    }


    public List<Operation> getOperationList(Long userId, LocalDate startDate, LocalDate finishDate) {

        List<Operation> operationList = operationRepository.findAllByIdAndDateBetween(userId, startDate, finishDate);
        if (operationList.isEmpty()) {
            throw new EntityNotFoundException("Данные в указанный период отсутствуют");
        }
        return operationList;
    }

    @Transactional
    public Operation addOperation(OperationType type, Long summa, Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow();
        Operation operation = new Operation();
        operation.setDate(LocalDate.now());
        operation.setOperationType(type);
        operation.setSumma(summa);
        operation.setUser(user);
        return operationRepository.save(operation);
    }
}
