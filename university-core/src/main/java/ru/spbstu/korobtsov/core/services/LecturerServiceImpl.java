package ru.spbstu.korobtsov.core.services;

import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.LecturerService;
import ru.spbstu.korobtsov.api.domain.Lecturer;
import ru.spbstu.korobtsov.api.exceptions.LecturerNotFoundException;
import ru.spbstu.korobtsov.core.repositories.LecturerRepository;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Lecturer create(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public Iterable<Lecturer> readAll() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer readOne(String id) {
        return lecturerRepository.findById(id).orElseThrow(() -> new LecturerNotFoundException(id));
    }

    @Override
    public Lecturer update(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public void delete(String id) {
        lecturerRepository.deleteById(id);
    }
}
