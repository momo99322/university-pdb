package ru.spbstu.korobtsov.api.exceptions;

public class LectureNotFoundException extends RuntimeException {

    public LectureNotFoundException(String id) {
        super("Lecture with id %s not found".formatted(id));
    }
}
