package com.example.terminal.response;

import com.example.terminal.dto.BalanceDTO;
import lombok.Getter;

@Getter
public enum ResponseResult {
    ERROR_OPERATION (0),
    SUCCESSFUL_OPERATION (1),
    TOTAL_ERROR (-1);
    private int result;

    ResponseResult(int result) {
        this.result = result;
    }


}
