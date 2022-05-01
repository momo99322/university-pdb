package ru.spbstu.korobtsov.api.exceptions;

public class LecturerNotFoundException extends RuntimeException {

    public LecturerNotFoundException(String message) {
        super(message);
    }
}
