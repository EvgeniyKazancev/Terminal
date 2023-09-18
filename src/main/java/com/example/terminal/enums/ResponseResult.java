package com.example.terminal.enums;

import lombok.Getter;

@Getter
public enum ResponseResult {
    ERROR_OPERATION (0),
    SUCCESSFUL_OPERATION (1),
    TOTAL_ERROR (-1),
    MONEY_RECEIVED (2),
    MONEY_SEND(3);
    private int result;

    ResponseResult(int result) {
        this.result = result;
    }


}
