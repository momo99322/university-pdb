package ru.spbstu.korobtsov.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.domain.Homework;
import ru.spbstu.korobtsov.api.exceptions.HomeworkNotFoundException;
import ru.spbstu.korobtsov.core.repositories.HomeworkRepository;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeworkServiceImpl.class);

    private final HomeworkRepository homeworkRepository;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public Homework create(Homework homework) {
        LOGGER.debug("Creating {}", homework);
        var createdHomework = homeworkRepository.save(homework);
        LOGGER.debug("Created {}", createdHomework);
        return createdHomework;
    }

    @Override
    public Iterable<Homework> readAll() {
        LOGGER.debug("Finding all");
        var homework = homeworkRepository.findAll();
        LOGGER.debug("Found {}", homework);
        return homework;
    }

    @Override
    public Homework readOne(String id) {
        LOGGER.debug("Finding by id={}", id);
        var homework = homeworkRepository.findById(id).orElseThrow(() -> new HomeworkNotFoundException(id));
        LOGGER.debug("Found {}", homework);
        return homework;
    }

    @Override
    public Homework update(Homework homework) {
        LOGGER.debug("Updating {}", homework);
        var updatedHomework = homeworkRepository.save(homework);
        LOGGER.debug("Updated {}", updatedHomework);
        return updatedHomework;
    }

    @Override
    public void delete(String id) {
        LOGGER.debug("Deleting by id={}", id);
        homeworkRepository.deleteById(id);
        LOGGER.debug("Deleted by id={}", id);
    }
}
