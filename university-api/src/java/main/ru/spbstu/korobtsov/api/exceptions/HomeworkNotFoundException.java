package ru.spbstu.korobtsov.api.exceptions;

public class HomeworkNotFoundException extends RuntimeException {

    public HomeworkNotFoundException(String message) {
        super(message);
    }
}
