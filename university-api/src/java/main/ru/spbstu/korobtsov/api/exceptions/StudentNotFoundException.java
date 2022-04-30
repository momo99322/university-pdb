package ru.spbstu.korobtsov.api.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String id) {
        super("Student with id %s not found".formatted(id));
    }
}
