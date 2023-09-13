package com.example.terminal.exeption;

import com.example.terminal.response.ResponseMessage;
import com.example.terminal.enums.ResponseResult;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerTerminal extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ResponseMessage> handleEntityNotFoundException(EntityNotFoundException  ex) {
        ResponseResult rs =  ResponseResult.TOTAL_ERROR;
        ResponseMessage rm = new ResponseMessage(ex.getMessage(), rs.getResult());
        return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
    }


}
