package com.example.terminal.dto;

import com.example.terminal.enums.OperationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OperationDTO implements Serializable {
    private OperationType operationType;
    private ZonedDateTime date;
    private Long summa;
}
