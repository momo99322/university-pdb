package ru.spbstu.korobtsov.core.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Homework;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.exceptions.notfound.LectureNotFoundException;
import ru.spbstu.korobtsov.api.exceptions.services.LectureServiceException;
import ru.spbstu.korobtsov.core.repositories.LectureRepository;

import javax.transaction.Transactional;

@Slf4j
@Service
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final StudentService studentService;
    private final HomeworkService homeworkService;

    public LectureServiceImpl(LectureRepository lectureRepository, StudentService studentService, HomeworkService homeworkService) {
        this.lectureRepository = lectureRepository;
        this.studentService = studentService;
        this.homeworkService = homeworkService;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Lecture create(Lecture lecture) {
        log.debug("Creating {}", lecture);
        try {
            var createdLecture = lectureRepository.save(lecture);

            studentService.readAll().forEach(
                    student -> homeworkService.create(
                            new Homework(null, "Дз по %s".formatted(createdLecture.getName()), "", -1, student, createdLecture))
            );

            log.debug("Created {}", createdLecture);
            return createdLecture;
        } catch (Exception exception) {
            throw new LectureServiceException("Error while creating %s, cause: %s".formatted(lecture, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Iterable<Lecture> readAll() {
        log.debug("Finding all");
        try {
            var lectures = lectureRepository.findAll();
            log.debug("Found {}", lectures);
            return lectures;
        } catch (Exception exception) {
            throw new LectureServiceException("Error while reading all, cause: %s".formatted(exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Lecture readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var lecture = lectureRepository.findById(id).orElseThrow(() -> new LectureNotFoundException("Lecture with id=%s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, lecture);
            return lecture;
        } catch (Exception exception) {
            throw new LectureServiceException("Error while read lecture by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Lecture readOneByName(String name) {
        log.debug("Finding by name={}", name);
        try {
            var lecture = lectureRepository.findByName(name).orElseThrow(() -> new LectureNotFoundException("Lecture with name=%s not found".formatted(name)));
            log.debug("Found by name={}, {}", name, lecture);
            return lecture;
        } catch (Exception exception) {
            throw new LectureServiceException("Error while read lecture by name=%s, cause: %s".formatted(name, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Lecture update(Lecture lecture) {
        log.debug("Updating {}", lecture);
        try {
            var updatedLecture = this.lectureRepository;
            log.debug("Updated {}", updatedLecture);
            return updatedLecture.save(lecture);
        } catch (Exception exception) {
            throw new LectureServiceException("Error while updating %s, cause: %s".formatted(lecture, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {
            lectureRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new LectureServiceException("Error while deleting by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }
}
