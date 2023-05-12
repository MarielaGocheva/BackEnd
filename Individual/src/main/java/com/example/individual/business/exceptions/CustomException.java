package com.example.individual.business.exceptions;

public class CustomException extends RuntimeException{
    public CustomException(String errorMessage){
        super(errorMessage);
    }
}
