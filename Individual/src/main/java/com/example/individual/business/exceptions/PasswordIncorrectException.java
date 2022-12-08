package com.example.individual.business.exceptions;

public class PasswordIncorrectException extends CustomException{
    public PasswordIncorrectException(){
        super("Incorrect password");
    };
}
