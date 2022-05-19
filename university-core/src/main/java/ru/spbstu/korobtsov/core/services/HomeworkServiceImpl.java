package ru.spbstu.korobtsov.core.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.domain.Homework;
import ru.spbstu.korobtsov.api.exceptions.notfound.HomeworkNotFoundException;
import ru.spbstu.korobtsov.api.exceptions.services.HomeworkServiceException;
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
        try {
            var createdHomework = homeworkRepository.save(homework);
            log.debug("Created {}", createdHomework);
            return createdHomework;
        } catch (Exception exception) {
            throw new HomeworkServiceException("Error while creating %s, cause: %s".formatted(homework, exception.getMessage()), exception);
        }
    }

    @Override
    public Iterable<Homework> readAll() {
        log.debug("Finding all");
        try {
            var homework = homeworkRepository.findAll();
            log.debug("Found {}", homework);
            return homework;
        } catch (Exception exception) {
            throw new HomeworkServiceException("Error while reading all, cause: %s".formatted(exception.getMessage()), exception);
        }
    }

    @Override
    public Homework readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var homework = homeworkRepository.findById(id).orElseThrow(() -> new HomeworkNotFoundException("Homework with=id %s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, homework);
            return homework;
        } catch (Exception exception) {
            throw new HomeworkServiceException("Error while read homework by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    public Homework update(Homework homework) {
        log.debug("Updating {}", homework);
        try {
            var updatedHomework = homeworkRepository.save(homework);
            log.debug("Updated {}", updatedHomework);
            return updatedHomework;
        } catch (Exception exception) {
            throw new HomeworkServiceException("Error while updating %s, cause: %s".formatted(homework, exception.getMessage()), exception);
        }
    }

    @Override
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {
            homeworkRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new HomeworkServiceException("Error while deleting by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }
}
