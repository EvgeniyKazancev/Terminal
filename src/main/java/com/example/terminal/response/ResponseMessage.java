package com.example.terminal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseMessage {
    String message ;
    int code;
}
