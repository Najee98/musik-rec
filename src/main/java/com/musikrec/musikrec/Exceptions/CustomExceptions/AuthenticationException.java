package com.musikrec.musikrec.Exceptions.CustomExceptions;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public AuthenticationException(String exceptionMessage, Throwable exceptionCause) {
        super(exceptionMessage, exceptionCause);
    }

}
