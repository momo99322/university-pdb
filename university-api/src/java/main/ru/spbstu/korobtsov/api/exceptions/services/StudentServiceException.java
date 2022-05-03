package ru.spbstu.korobtsov.api.exceptions.services;

public class StudentServiceException extends ServiceException {

    public StudentServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentServiceException(String message) {
        super(message);
    }

    public StudentServiceException(Throwable cause) {
        super(cause);
    }
}
