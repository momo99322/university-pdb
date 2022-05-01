package ru.spbstu.korobtsov.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Student;
import ru.spbstu.korobtsov.api.exceptions.StudentNotFoundException;
import ru.spbstu.korobtsov.core.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        LOGGER.debug("Creating {}", student);
        var createdStudent = studentRepository.save(student);
        LOGGER.debug("Created {}", createdStudent);
        return createdStudent;
    }

    @Override
    public Iterable<Student> readAll() {
        LOGGER.debug("Finding all");
        var students = studentRepository.findAll();
        LOGGER.debug("Found {}", students);
        return students;
    }

    @Override
    public Student readOne(String id) {
        LOGGER.debug("Finding by id={}", id);
        var student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id=%s not found".formatted(id)));
        LOGGER.debug("Found {}", student);
        return student;
    }

    @Override
    public Student readOneByName(String name) {
        LOGGER.debug("Finding by name={}", name);
        var student = studentRepository.findByName(name).orElseThrow(() -> new StudentNotFoundException("Student with name=%s not found".formatted(name)));
        LOGGER.debug("Found {}", student);
        return student;
    }

    @Override
    public Student update(Student student) {
        LOGGER.debug("Updating {}", student);
        var updatedStudent = studentRepository.save(student);
        LOGGER.debug("Updated {}", updatedStudent);
        return updatedStudent;
    }

    @Override
    public void delete(String id) {
        LOGGER.debug("Deleting by id={}", id);
        studentRepository.deleteById(id);
        LOGGER.debug("Deleted by id={}", id);
    }
}
