package com.alkemy.cysjava.virtualwallet.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message){
        super(message);
    }
}
