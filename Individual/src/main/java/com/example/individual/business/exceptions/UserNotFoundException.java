package com.example.individual.business.exceptions;

public class UserNotFoundException extends CustomException{
    public UserNotFoundException(){
        super("Incorrect password");
    };
}
