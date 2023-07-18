package com.ritam.user.service.exception;

public class ResourceNotFroundException extends RuntimeException{
    public ResourceNotFroundException(){
        super("Resource not found on server");
    }

    public ResourceNotFroundException(String message){
        super(message);
    }
}
