package ru.spbstu.korobtsov.core.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Homework;
import ru.spbstu.korobtsov.api.domain.Student;
import ru.spbstu.korobtsov.api.exceptions.notfound.StudentNotFoundException;
import ru.spbstu.korobtsov.api.exceptions.services.StudentServiceException;
import ru.spbstu.korobtsov.core.notification.sms.SmsNotificationService;
import ru.spbstu.korobtsov.core.repositories.StudentRepository;

import javax.transaction.Transactional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SmsNotificationService smsNotificationService;

    @Value("${university.sms.phone}")
    private String universityPhone;

    @Value("${university.sms.message}")
    private String messageForStudent;

    @Value("${university.sms.allowable-avg-grade}")
    private Double allowableAverageGrade;

    public StudentServiceImpl(StudentRepository studentRepository, SmsNotificationService smsNotificationService) {
        this.studentRepository = studentRepository;
        this.smsNotificationService = smsNotificationService;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Student create(Student student) {
        log.debug("Creating {}", student);
        try {
            var createdStudent = studentRepository.save(student);
            log.debug("Created {}", createdStudent);
            return createdStudent;
        } catch (Exception exception) {
            throw new StudentServiceException("Error while creating %s, cause: %s".formatted(student, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Iterable<Student> readAll() {
        log.debug("Finding all");
        try {
            var students = studentRepository.findAll();
            log.debug("Found {}", students);
            return students;
        } catch (Exception exception) {
            throw new StudentServiceException("Error while reading all, cause: %s".formatted(exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Student readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var student = studentRepository.findById(id)
                    .orElseThrow(() -> new StudentNotFoundException("Student with id=%s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, student);
            return student;
        } catch (Exception exception) {
            throw new StudentServiceException("Error while read student by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Student readOneByName(String name) {
        log.debug("Finding by name={}", name);
        try {
            var student = studentRepository.findByName(name)
                    .orElseThrow(() -> new StudentNotFoundException("Student with name=%s not found".formatted(name)));
            log.debug("Found by name={}, {}", name, student);
            return student;
        } catch (Exception exception) {
            throw new StudentServiceException("Error while read student by name=%s, cause: %s".formatted(name, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Student update(Student student) {
        log.debug("Updating {}", student);
        try {
            studentRepository.findById(student.getId())
                    .orElseThrow(() -> new StudentNotFoundException("Student with id=%s not found".formatted((student.getId()))));
            var updatedStudent = studentRepository.save(student);
            log.debug("Updated {}", updatedStudent);
            return updatedStudent;
        } catch (Exception exception) {
            throw new StudentServiceException("Error while updating %s, cause: %s".formatted(student, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {

            studentRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new StudentServiceException("Error while deleting by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    public void checkStudentAverageMarkAndSendMessageIfItLessThanAllowed(Student student) {
        var average = student.getHomework()
                .stream()
                .mapToInt(Homework::getMark)
                .filter(mark -> mark >= 0)
                .average();

        if (average.isPresent() && average.getAsDouble() < allowableAverageGrade) {
            log.debug("{} has average grade {} less than allowable {}", student, average, allowableAverageGrade);
            smsNotificationService.send(universityPhone, student.getPhone(), messageForStudent);
        }
    }
}
