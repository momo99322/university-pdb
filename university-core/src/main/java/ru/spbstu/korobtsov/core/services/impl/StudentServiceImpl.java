package ru.spbstu.korobtsov.core.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Attendance;
import ru.spbstu.korobtsov.api.domain.Homework;
import ru.spbstu.korobtsov.api.domain.Student;
import ru.spbstu.korobtsov.api.exceptions.notfound.StudentNotFoundException;
import ru.spbstu.korobtsov.api.exceptions.services.StudentServiceException;
import ru.spbstu.korobtsov.core.notification.email.EmailNotificationService;
import ru.spbstu.korobtsov.core.notification.sms.SmsNotificationService;
import ru.spbstu.korobtsov.core.properties.EmailSendingProperties;
import ru.spbstu.korobtsov.core.properties.SmsSendingProperties;
import ru.spbstu.korobtsov.core.repositories.StudentRepository;
import ru.spbstu.korobtsov.core.services.InternalStudentService;

import javax.transaction.Transactional;
import java.util.function.Predicate;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService, InternalStudentService {

    private final StudentRepository studentRepository;
    private final SmsNotificationService smsNotificationService;
    private final EmailNotificationService emailNotificationService;
    private final SmsSendingProperties smsSendingProperties;

    private final EmailSendingProperties emailSendingProperties;

    public StudentServiceImpl(StudentRepository studentRepository, SmsNotificationService smsNotificationService, EmailNotificationService emailNotificationService, SmsSendingProperties smsSendingProperties, EmailSendingProperties emailSendingProperties) {
        this.studentRepository = studentRepository;
        this.smsNotificationService = smsNotificationService;
        this.emailNotificationService = emailNotificationService;
        this.smsSendingProperties = smsSendingProperties;
        this.emailSendingProperties = emailSendingProperties;
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
        log.debug("Checking average mark for {}", student);
        var averageMark = student.getHomework()
                .stream()
                .mapToInt(Homework::getMark)
                .filter(mark -> mark >= 0)
                .average();

        log.debug("{} has average mark={}", student, averageMark);
        if (averageMark.isPresent() && averageMark.getAsDouble() < smsSendingProperties.getAllowableAvgGrade()) {
            log.debug("{} has average grade {} less than allowed {}", student, averageMark.getAsDouble(), smsSendingProperties.getAllowableAvgGrade());
            smsNotificationService.send(smsSendingProperties.getPhone(), student.getPhone(), smsSendingProperties.getMessage());
        }
    }

    @Override
    public boolean checkStudentAttendance(Student student) {
        log.debug("Checking attendance for {}", student);

        var missedLecturesCount = student.getAttendance()
                .stream()
                .filter(Predicate.not(Attendance::isAttendance))
                .count();

        log.debug("{} has {} missed lectures", student, missedLecturesCount);
        var needAttention = missedLecturesCount >= emailSendingProperties.getMaxAllowedMissedLectureCount();

        if (needAttention) {
            log.debug("{} has {} missed lectures more than allowed {}", student, missedLecturesCount, emailSendingProperties.getMaxAllowedMissedLectureCount());
        }
        return needAttention;
    }

    @Override
    public void sendAttention(Student student) {
        emailNotificationService.send(emailSendingProperties.getEmail(), student.getEmail(), emailSendingProperties.getMessageForStudent());
    }
}
