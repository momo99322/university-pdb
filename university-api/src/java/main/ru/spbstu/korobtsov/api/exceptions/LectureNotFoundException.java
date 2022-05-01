package ru.spbstu.korobtsov.api.exceptions;

public class LectureNotFoundException extends RuntimeException {

    public LectureNotFoundException(String message) {
        super(message);
    }
}
