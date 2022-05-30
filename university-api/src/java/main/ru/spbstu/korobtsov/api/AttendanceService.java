package ru.spbstu.korobtsov.api;

import ru.spbstu.korobtsov.api.domain.Attendance;

public interface AttendanceService {

    Attendance create(Attendance attendance);

    Iterable<Attendance> readAll();

    Attendance readOne(String id);

    Attendance update(Attendance attendance);

    void delete(String id);
}
