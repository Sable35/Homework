package ru.sber.exceptions;

public class NullArgException extends RuntimeException{
    public NullArgException(String message) {
        super(message);
    }
}
