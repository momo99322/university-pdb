package ru.spbstu.korobtsov.core.services;

import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.exceptions.LectureNotFoundException;
import ru.spbstu.korobtsov.core.repositories.LectureRepository;

@Service
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    public LectureServiceImpl(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public Lecture create(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public Iterable<Lecture> readAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture readOne(String id) {
        return lectureRepository.findById(id).orElseThrow(() -> new LectureNotFoundException(id));
    }

    @Override
    public Lecture update(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public void delete(String id) {
        lectureRepository.deleteById(id);
    }
}
