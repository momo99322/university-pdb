package ru.spbstu.korobtsov.api.exceptions.services;

public class HomeworkServiceException extends ServiceException {

    public HomeworkServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public HomeworkServiceException(String message) {
        super(message);
    }

    public HomeworkServiceException(Throwable cause) {
        super(cause);
    }
}
