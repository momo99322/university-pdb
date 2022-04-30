package ru.spbstu.korobtsov.api.exceptions;

public class HomeworkNotFoundException extends RuntimeException {

    public HomeworkNotFoundException(String id) {
        super("Homework with id %s not found".formatted(id));
    }
}
