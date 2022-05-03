package ru.spbstu.korobtsov.api.exceptions.services;

public abstract class ServiceException extends RuntimeException {

    protected ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    protected ServiceException(String message) {
        super(message);
    }

    protected ServiceException(Throwable cause) {
        super(cause);
    }
}
