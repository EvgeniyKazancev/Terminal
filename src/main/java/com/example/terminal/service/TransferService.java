package com.example.terminal.service;

import com.example.terminal.entity.Operation;
import com.example.terminal.entity.Transfer;
import com.example.terminal.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
         private  final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Transfer addTransfer(Operation spendUserId, Operation recipientUserId){
        Transfer transfer = new Transfer();
        transfer.setSenderOperation(spendUserId);
        transfer.setRecipientOperation(recipientUserId);
        return transferRepository.save(transfer);
    }
}
