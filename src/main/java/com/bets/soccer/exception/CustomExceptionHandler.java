package com.bets.soccer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(value = RecordAlreadyExistsException.class)
    public ResponseEntity handleRecordAlreadyExistsException(RecordAlreadyExistsException exception)
    {
        return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
    }

}
