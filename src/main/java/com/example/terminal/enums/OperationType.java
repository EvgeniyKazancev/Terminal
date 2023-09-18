package com.example.terminal.enums;

import lombok.Getter;

@Getter
public enum OperationType {
    PUT_MONEY ("Put_Money"),
    TAKE_MONEY ("Take_money"),
    TRANSFER_RECEIVING ("Transfer_money_receiving"),
    TRANSFER_SEND ("Transfer_money_send");
    private  String type;
    OperationType(String type) {
       this.type = type;
    }
}
