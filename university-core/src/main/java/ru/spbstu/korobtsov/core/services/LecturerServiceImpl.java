package ru.spbstu.korobtsov.core.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.LecturerService;
import ru.spbstu.korobtsov.api.domain.Lecturer;
import ru.spbstu.korobtsov.api.exceptions.LecturerNotFoundException;
import ru.spbstu.korobtsov.core.repositories.LecturerRepository;

@Slf4j
@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Lecturer create(Lecturer lecturer) {
        log.debug("Creating {}", lecturer);
        var createdLecturer = lecturerRepository.save(lecturer);
        log.debug("Created {}", createdLecturer);
        return createdLecturer;
    }

    @Override
    public Iterable<Lecturer> readAll() {
        log.debug("Finding all");
        var lecturers = lecturerRepository.findAll();
        log.debug("Found {}", lecturers);
        return lecturers;
    }

    @Override
    public Lecturer readOne(String id) {
        log.debug("Finding by id={}", id);
        var lecturer = lecturerRepository.findById(id).orElseThrow(() -> new LecturerNotFoundException("Lecturer with=id %s not found".formatted(id)));
        log.debug("Found by id={}, {}", id, lecturer);
        return lecturer;
    }

    @Override
    public Lecturer update(Lecturer lecturer) {
        log.debug("Updating {}", lecturer);
        var updatedLecturer = lecturerRepository.save(lecturer);
        log.debug("Updated {}", updatedLecturer);
        return updatedLecturer;
    }

    @Override
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        lecturerRepository.deleteById(id);
        log.debug("Deleted by id={}", id);
    }
}
