package com.example.terminal.service;

import com.example.terminal.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TransferServiceTest {
    @Mock
    TransferRepository transferRepository;
    @InjectMocks
    TransferService transferService;

    @Test
    public void addTransfer() {

    }
}