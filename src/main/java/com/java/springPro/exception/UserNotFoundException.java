package com.java.springPro.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message){
        super(message);
    }

}