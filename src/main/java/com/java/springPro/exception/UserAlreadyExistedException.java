package com.java.springPro.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserAlreadyExistedException extends Exception {

    public UserAlreadyExistedException(String message){
        super(message);
    }

}
