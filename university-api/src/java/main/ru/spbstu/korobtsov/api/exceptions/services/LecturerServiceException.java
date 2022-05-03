package ru.spbstu.korobtsov.api.exceptions.services;

public class LecturerServiceException extends ServiceException {

    public LecturerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LecturerServiceException(String message) {
        super(message);
    }

    public LecturerServiceException(Throwable cause) {
        super(cause);
    }
}
