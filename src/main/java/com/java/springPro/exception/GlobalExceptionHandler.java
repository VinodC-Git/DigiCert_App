package com.java.springPro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserAlreadyExistedException.class)
    public ResponseEntity<Object> handleUserAlreadyExistedException(UserAlreadyExistedException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT.value())
                .body("User Already Existed");
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> UserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body("User Already Existed");
    }
}
