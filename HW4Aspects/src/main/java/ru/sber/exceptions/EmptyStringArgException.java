package ru.sber.exceptions;

public class EmptyStringArgException extends RuntimeException{
    public EmptyStringArgException(String message) {
        super(message);
    }
}
