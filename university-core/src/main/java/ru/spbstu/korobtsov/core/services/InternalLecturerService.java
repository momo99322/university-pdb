package ru.spbstu.korobtsov.core.services;

import ru.spbstu.korobtsov.api.domain.Lecturer;

public interface InternalLecturerService {

    void sendAttention(Lecturer lecturer);
}
