package com.portal.api.exception.domain;

public class UserNameExistException extends Exception {
    public UserNameExistException(String message){
        super(message);
    }
}
