package ru.spbstu.korobtsov.api.exceptions.services;

public class AttendanceServiceException extends ServiceException {

    public AttendanceServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttendanceServiceException(String message) {
        super(message);
    }

    public AttendanceServiceException(Throwable cause) {
        super(cause);
    }
}