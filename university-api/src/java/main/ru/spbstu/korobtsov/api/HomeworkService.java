package ru.spbstu.korobtsov.api;

import ru.spbstu.korobtsov.api.domain.Homework;

public interface HomeworkService {

    Homework create(Homework homework);

    Iterable<Homework> readAll();

    Homework readOne(String id);

    Homework update(Homework homework);

    void delete(String id);
}
