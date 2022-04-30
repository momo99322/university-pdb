package ru.spbstu.korobtsov.core.services;

import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Student;
import ru.spbstu.korobtsov.api.exceptions.StudentNotFoundException;
import ru.spbstu.korobtsov.core.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Iterable<Student> readAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student readOne(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(String id) {
        studentRepository.deleteById(id);
    }
}
