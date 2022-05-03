package ru.spbstu.korobtsov.core.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.domain.Homework;
import ru.spbstu.korobtsov.api.exceptions.HomeworkNotFoundException;
import ru.spbstu.korobtsov.core.repositories.HomeworkRepository;

@Slf4j
@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public Homework create(Homework homework) {
        log.debug("Creating {}", homework);
        var createdHomework = homeworkRepository.save(homework);
        log.debug("Created {}", createdHomework);
        return createdHomework;
    }

    @Override
    public Iterable<Homework> readAll() {
        log.debug("Finding all");
        var homework = homeworkRepository.findAll();
        log.debug("Found {}", homework);
        return homework;
    }

    @Override
    public Homework readOne(String id) {
        log.debug("Finding by id={}", id);
        var homework = homeworkRepository.findById(id).orElseThrow(() -> new HomeworkNotFoundException("Homework with=id %s not found".formatted(id)));
        log.debug("Found by id={}, {}", id, homework);
        return homework;
    }

    @Override
    public Homework update(Homework homework) {
        log.debug("Updating {}", homework);
        var updatedHomework = homeworkRepository.save(homework);
        log.debug("Updated {}", updatedHomework);
        return updatedHomework;
    }

    @Override
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        homeworkRepository.deleteById(id);
        log.debug("Deleted by id={}", id);
    }
}
