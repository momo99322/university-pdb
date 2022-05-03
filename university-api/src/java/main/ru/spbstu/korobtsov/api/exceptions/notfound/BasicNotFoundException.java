package ru.spbstu.korobtsov.api.exceptions.notfound;

public abstract class BasicNotFoundException extends RuntimeException {

    public BasicNotFoundException(String message) {
        super(message);
    }
}
