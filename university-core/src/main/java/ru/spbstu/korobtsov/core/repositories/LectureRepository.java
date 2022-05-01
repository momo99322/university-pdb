package ru.spbstu.korobtsov.core.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.korobtsov.api.domain.Lecture;

import java.util.Optional;

public interface LectureRepository extends CrudRepository<Lecture, String> {
    Optional<Lecture> findByName(String name);
}
