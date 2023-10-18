package com.example.terminal.dto;

import com.example.terminal.entity.Transfer;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
public class TransferDto implements Serializable {

    private  Long id;

    private  Long senderId;

    private  Long recipientId;
}