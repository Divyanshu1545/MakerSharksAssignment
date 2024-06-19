package com.divyanshu.assignment.exceptions;

public class PhoneNumberAlreadyExistsException extends RuntimeException{
    PhoneNumberAlreadyExistsException(String message){
        super(message);
    }
}
