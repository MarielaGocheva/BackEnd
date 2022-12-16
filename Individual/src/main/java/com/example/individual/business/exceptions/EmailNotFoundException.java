package com.example.individual.business.exceptions;

public class EmailNotFoundException extends CustomException{
    public EmailNotFoundException(){
        super("Email not found");
    };
}
