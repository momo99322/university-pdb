package ru.spbstu.korobtsov.core.services;

import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.domain.Homework;
import ru.spbstu.korobtsov.api.exceptions.HomeworkNotFoundException;
import ru.spbstu.korobtsov.core.repositories.HomeworkRepository;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public Homework create(Homework homework) {
        return homeworkRepository.save(homework);
    }

    @Override
    public Iterable<Homework> readAll() {
        return homeworkRepository.findAll();
    }

    @Override
    public Homework readOne(String id) {
        return homeworkRepository.findById(id).orElseThrow(() -> new HomeworkNotFoundException(id));
    }

    @Override
    public Homework update(Homework homework) {
        return homeworkRepository.save(homework);
    }

    @Override
    public void delete(String id) {
        homeworkRepository.deleteById(id);
    }
}
