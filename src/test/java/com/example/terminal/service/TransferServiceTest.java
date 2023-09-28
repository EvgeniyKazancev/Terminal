package com.example.terminal.service;

import com.example.terminal.entity.Operation;
import com.example.terminal.entity.Transfer;
import com.example.terminal.repository.TransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {


    @Test
    public void testAddTransfer() {
        Operation spendUserId= new Operation();
        Operation recipientUserId = new Operation();

        TransferRepository transferRepository = Mockito.mock(TransferRepository.class);
        TransferService transferService = new TransferService(transferRepository);

        when(transferRepository.save(Mockito.any(Transfer.class))).thenReturn(new Transfer());

        Transfer result = transferService.addTransfer(spendUserId,recipientUserId);
        verify(transferRepository).save(Mockito.any(Transfer.class));

        assertThat(result).isNotNull();

    }
    @Test
    public void testAddTransfer_SavesTransferToRepository() {

        Operation spendUserId = new Operation();
        Operation recipientUserId = new Operation();

        TransferRepository transferRepository = Mockito.mock(TransferRepository.class);
        TransferService transferService = new TransferService(transferRepository);

        Transfer expectedTransfer = new Transfer();
        when(transferRepository.save(Mockito.any(Transfer.class))).thenReturn(expectedTransfer);

        Transfer result = transferService.addTransfer(spendUserId, recipientUserId);
        verify(transferRepository).save(Mockito.any(Transfer.class));

        Assertions.assertEquals(expectedTransfer, result);
    }
}