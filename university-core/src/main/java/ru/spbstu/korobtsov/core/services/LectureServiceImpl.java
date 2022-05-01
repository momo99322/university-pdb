package ru.spbstu.korobtsov.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.exceptions.LectureNotFoundException;
import ru.spbstu.korobtsov.core.repositories.LectureRepository;

@Service
public class LectureServiceImpl implements LectureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LectureServiceImpl.class);

    private final LectureRepository lectureRepository;

    public LectureServiceImpl(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public Lecture create(Lecture lecture) {
        LOGGER.debug("Creating {}", lecture);
        var createdLecture = lectureRepository.save(lecture);
        LOGGER.debug("Created {}", createdLecture);
        return createdLecture;
    }

    @Override
    public Iterable<Lecture> readAll() {
        LOGGER.debug("Finding all");
        var lectures = lectureRepository.findAll();
        LOGGER.debug("Found {}", lectures);
        return lectures;
    }

    @Override
    public Lecture readOne(String id) {
        LOGGER.debug("Finding by id={}", id);
        var lecture = lectureRepository.findById(id).orElseThrow(() -> new LectureNotFoundException("Lecture with id=%s not found".formatted(id)));
        LOGGER.debug("Found by id={} {}", id, lecture);
        return lecture;
    }

    @Override
    public Lecture readOneByName(String name) {
        LOGGER.debug("Finding by name={}", name);
        var lecture = lectureRepository.findByName(name).orElseThrow(() -> new LectureNotFoundException("Lecture with name=%s not found".formatted(name)));
        LOGGER.debug("Found by name={} {}", name, lecture);
        return lecture;
    }

    @Override
    public Lecture update(Lecture lecture) {
        LOGGER.debug("Updating {}", lecture);
        var updatedLecture = this.lectureRepository;
        LOGGER.debug("Updated {}", updatedLecture);
        return updatedLecture.save(lecture);
    }

    @Override
    public void delete(String id) {
        LOGGER.debug("Deleting by id={}", id);
        lectureRepository.deleteById(id);
        LOGGER.debug("Deleted by id={}", id);
    }
}
