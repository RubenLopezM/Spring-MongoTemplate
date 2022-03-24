package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomExceptios extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<CustomError> handleNotFoundException(PersonNotFoundException p){
        CustomError error=new CustomError(new Date(), HttpStatus.NOT_FOUND.value(),p.getMessage());
        return new ResponseEntity<CustomError>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableException.class)
    public final ResponseEntity<CustomError> handleUnprocessableException(UnprocessableException u){
        CustomError error=new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(),u.getMessage());
        return new ResponseEntity<CustomError>(error,HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
