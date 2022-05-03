package ru.spbstu.korobtsov.core.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Student;
import ru.spbstu.korobtsov.api.exceptions.StudentNotFoundException;
import ru.spbstu.korobtsov.core.repositories.StudentRepository;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        log.debug("Creating {}", student);
        var createdStudent = studentRepository.save(student);
        log.debug("Created {}", createdStudent);
        return createdStudent;
    }

    @Override
    public Iterable<Student> readAll() {
        log.debug("Finding all");
        var students = studentRepository.findAll();
        log.debug("Found {}", students);
        return students;
    }

    @Override
    public Student readOne(String id) {
        log.debug("Finding by id={}", id);
        var student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id=%s not found".formatted(id)));
        log.debug("Found  by id={}, {}", id, student);
        return student;
    }

    @Override
    public Student readOneByName(String name) {
        log.debug("Finding by name={}", name);
        var student = studentRepository.findByName(name).orElseThrow(() -> new StudentNotFoundException("Student with name=%s not found".formatted(name)));
        log.debug("Found by name={}, {}", name, student);
        return student;
    }

    @Override
    public Student update(Student student) {
        log.debug("Updating {}", student);
        var updatedStudent = studentRepository.save(student);
        log.debug("Updated {}", updatedStudent);
        return updatedStudent;
    }

    @Override
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        studentRepository.deleteById(id);
        log.debug("Deleted by id={}", id);
    }
}
