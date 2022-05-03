package ru.spbstu.korobtsov.api.exceptions.services;

public class LectureServiceException extends ServiceException {

    public LectureServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LectureServiceException(String message) {
        super(message);
    }

    public LectureServiceException(Throwable cause) {
        super(cause);
    }
}
