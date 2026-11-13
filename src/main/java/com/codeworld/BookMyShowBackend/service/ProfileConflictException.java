package com.codeworld.BookMyShowBackend.service;

public class ProfileConflictException extends RuntimeException{

    public ProfileConflictException(String message)
    {
        super(message);
    }
}
