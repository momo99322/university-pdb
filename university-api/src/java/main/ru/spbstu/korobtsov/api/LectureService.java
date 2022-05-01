package ru.spbstu.korobtsov.api;

import ru.spbstu.korobtsov.api.domain.Lecture;

public interface LectureService {

    Lecture create(Lecture lecture);

    Iterable<Lecture> readAll();

    Lecture readOne(String id);

    Lecture readOneByName(String name);

    Lecture update(Lecture lecture);

    void delete(String id);
}
