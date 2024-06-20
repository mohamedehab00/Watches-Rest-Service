package com.rest.watchrestservice.exceptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String message){
        super(message);
    }
}
