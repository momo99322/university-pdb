package ru.spbstu.korobtsov.core.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.korobtsov.api.domain.Lecturer;

public interface LecturerRepository extends CrudRepository<Lecturer, String> {
}
