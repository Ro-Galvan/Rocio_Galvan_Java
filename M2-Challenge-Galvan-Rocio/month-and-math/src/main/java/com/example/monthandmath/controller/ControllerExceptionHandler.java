package com.example.monthandmath.controller;

import com.example.monthandmath.model.CustomErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//Code referenced from echo-range-service activity in class on 10/5/22

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    null should be entered as an operand, instead of an int, in postman or insomnia to generate 422 error
    public ResponseEntity<CustomErrorResponse> handleNullOperandInput(IllegalArgumentException ex) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), ex.getMessage());

        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());

//        sending body (error) and any http status (HttpStatus) we want
        ResponseEntity<CustomErrorResponse> returnVal = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        return returnVal;
    }
}