package ru.spbstu.korobtsov.core.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.korobtsov.api.domain.Student;

public interface StudentRepository extends CrudRepository<Student, String> {
}