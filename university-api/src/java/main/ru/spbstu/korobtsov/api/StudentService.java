package ru.spbstu.korobtsov.api;

import ru.spbstu.korobtsov.api.domain.Student;

public interface StudentService {

    Student create(Student student);

    Iterable<Student> readAll();

    Student readOne(String id);

    Student update(Student student);

    void delete(String id);
}
