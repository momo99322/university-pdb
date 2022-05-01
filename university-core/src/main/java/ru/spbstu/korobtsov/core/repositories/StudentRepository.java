package ru.spbstu.korobtsov.core.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.korobtsov.api.domain.Student;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, String> {

    Optional<Student> findByName(String name);
}