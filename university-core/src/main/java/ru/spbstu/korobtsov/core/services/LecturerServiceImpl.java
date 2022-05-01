package ru.spbstu.korobtsov.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.LecturerService;
import ru.spbstu.korobtsov.api.domain.Lecturer;
import ru.spbstu.korobtsov.api.exceptions.LecturerNotFoundException;
import ru.spbstu.korobtsov.core.repositories.LecturerRepository;

@Service
public class LecturerServiceImpl implements LecturerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerServiceImpl.class);

    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Lecturer create(Lecturer lecturer) {
        LOGGER.debug("Creating {}", lecturer);
        var createdLecturer = lecturerRepository.save(lecturer);
        LOGGER.debug("Created {}", createdLecturer);
        return createdLecturer;
    }

    @Override
    public Iterable<Lecturer> readAll() {
        LOGGER.debug("Finding all");
        var lecturers = lecturerRepository.findAll();
        LOGGER.debug("Found {}", lecturers);
        return lecturers;
    }

    @Override
    public Lecturer readOne(String id) {
        LOGGER.debug("Finding by id={}", id);
        var lecturer = lecturerRepository.findById(id).orElseThrow(() -> new LecturerNotFoundException("Lecturer with=id %s not found".formatted(id)));
        LOGGER.debug("Found {}", lecturer);
        return lecturer;
    }

    @Override
    public Lecturer update(Lecturer lecturer) {
        LOGGER.debug("Updating {}", lecturer);
        var updatedLecturer = lecturerRepository.save(lecturer);
        LOGGER.debug("Updated {}", updatedLecturer);
        return updatedLecturer;
    }

    @Override
    public void delete(String id) {
        LOGGER.debug("Deleting by id={}", id);
        lecturerRepository.deleteById(id);
        LOGGER.debug("Deleted by id={}", id);
    }
}
