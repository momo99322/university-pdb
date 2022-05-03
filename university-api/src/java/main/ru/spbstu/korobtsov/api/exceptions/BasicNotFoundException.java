package ru.spbstu.korobtsov.api.exceptions;

public abstract class BasicNotFoundException extends RuntimeException {

    public BasicNotFoundException(String message) {
        super(message);
    }
}
