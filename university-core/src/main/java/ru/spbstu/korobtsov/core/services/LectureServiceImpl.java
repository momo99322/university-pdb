package ru.spbstu.korobtsov.core.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.exceptions.notfound.LectureNotFoundException;
import ru.spbstu.korobtsov.core.repositories.LectureRepository;

@Slf4j
@Service
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    public LectureServiceImpl(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public Lecture create(Lecture lecture) {
        log.debug("Creating {}", lecture);
        var createdLecture = lectureRepository.save(lecture);
        log.debug("Created {}", createdLecture);
        return createdLecture;
    }

    @Override
    public Iterable<Lecture> readAll() {
        log.debug("Finding all");
        var lectures = lectureRepository.findAll();
        log.debug("Found {}", lectures);
        return lectures;
    }

    @Override
    public Lecture readOne(String id) {
        log.debug("Finding by id={}", id);
        var lecture = lectureRepository.findById(id).orElseThrow(() -> new LectureNotFoundException("Lecture with id=%s not found".formatted(id)));
        log.debug("Found by id={}, {}", id, lecture);
        return lecture;
    }

    @Override
    public Lecture readOneByName(String name) {
        log.debug("Finding by name={}", name);
        var lecture = lectureRepository.findByName(name).orElseThrow(() -> new LectureNotFoundException("Lecture with name=%s not found".formatted(name)));
        log.debug("Found by name={}, {}", name, lecture);
        return lecture;
    }

    @Override
    public Lecture update(Lecture lecture) {
        log.debug("Updating {}", lecture);
        var updatedLecture = this.lectureRepository;
        log.debug("Updated {}", updatedLecture);
        return updatedLecture.save(lecture);
    }

    @Override
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        lectureRepository.deleteById(id);
        log.debug("Deleted by id={}", id);
    }
}
