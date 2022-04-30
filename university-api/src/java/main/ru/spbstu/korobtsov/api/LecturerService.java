package ru.spbstu.korobtsov.api;

import ru.spbstu.korobtsov.api.domain.Lecturer;

public interface LecturerService {

    Lecturer create(Lecturer lecturer);

    Iterable<Lecturer> readAll();

    Lecturer readOne(String id);

    Lecturer update(Lecturer lecturer);

    void delete(String id);
}
