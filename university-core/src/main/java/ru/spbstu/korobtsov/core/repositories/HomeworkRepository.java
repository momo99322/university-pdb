package ru.spbstu.korobtsov.core.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.korobtsov.api.domain.Homework;

public interface HomeworkRepository extends CrudRepository<Homework, String> {
}
