package ru.spbstu.korobtsov.api.exceptions;

public class LecturerNotFoundException extends RuntimeException {

    public LecturerNotFoundException(String id) {
        super("Lecturer with id %s not found".formatted(id));
    }
}
