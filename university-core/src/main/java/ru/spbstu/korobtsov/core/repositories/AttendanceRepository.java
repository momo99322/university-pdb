package ru.spbstu.korobtsov.core.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.korobtsov.api.domain.Attendance;

public interface AttendanceRepository extends CrudRepository<Attendance, String> {
}
