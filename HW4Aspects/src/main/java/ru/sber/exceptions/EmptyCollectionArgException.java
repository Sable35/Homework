package ru.sber.exceptions;

public class EmptyCollectionArgException extends RuntimeException{
    public EmptyCollectionArgException(String message) {
        super(message);
    }
}
