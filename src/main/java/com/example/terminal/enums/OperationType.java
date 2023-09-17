package com.example.terminal.enums;

import lombok.Getter;

@Getter
public enum OperationType {
    PUT_MONEY ("Put_Money"),
    TAKE_MONEY ("Take_money"),
    TRANSFER ("Transfer_money");
    private  String type;
    OperationType(String type) {
       this.type = type;
    }
}
