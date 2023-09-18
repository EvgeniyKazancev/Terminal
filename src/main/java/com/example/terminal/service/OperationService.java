package com.example.terminal.service;

import com.example.terminal.entity.Operation;
import com.example.terminal.entity.Transfer;
import com.example.terminal.entity.Users;
import com.example.terminal.enums.OperationType;
import com.example.terminal.repository.OperationRepository;
import com.example.terminal.repository.TransferRepository;
import com.example.terminal.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final UsersRepository usersRepository;
    private final TransferRepository transferRepository;
    public OperationService(OperationRepository operationRepository, UsersRepository usersRepository,@Lazy TransferRepository transferRepository) {
        this.operationRepository = operationRepository;
        this.usersRepository = usersRepository;
        this.transferRepository = transferRepository;
    }


    public List<Operation> getOperationList(Long userId, LocalDate startDate, LocalDate finishDate) {
        List<Operation> operationList = operationRepository.findAllByUserIdAndAndDateBetween(userId, startDate, finishDate);
        if (startDate == null){
         operationList = operationRepository.findAllByUserId(userId);
        }else if
        (operationList.isEmpty()) {
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
