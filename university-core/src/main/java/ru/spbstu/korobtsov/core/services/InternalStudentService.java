package ru.spbstu.korobtsov.core.services;

import ru.spbstu.korobtsov.api.domain.Student;

public interface InternalStudentService {

    void checkStudentAverageMarkAndSendMessageIfItLessThanAllowed(Student student);

    boolean checkStudentAttendance(Student student);

    void sendAttention(Student student);
}
