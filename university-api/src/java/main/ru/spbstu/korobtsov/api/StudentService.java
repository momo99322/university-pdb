package ru.spbstu.korobtsov.api;

import ru.spbstu.korobtsov.api.domain.Student;

public interface StudentService {

    Student create(Student student);

    Iterable<Student> readAll();

    Student readOne(String id);

    Student readOneByName(String name);

    Student update(Student student);

    void delete(String id);

    void checkStudentAverageMarkAndSendmessageIfItLess(Student student);
}
