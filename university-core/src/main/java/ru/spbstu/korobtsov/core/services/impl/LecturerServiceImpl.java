package ru.spbstu.korobtsov.core.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.LecturerService;
import ru.spbstu.korobtsov.api.domain.Lecturer;
import ru.spbstu.korobtsov.api.exceptions.notfound.LecturerNotFoundException;
import ru.spbstu.korobtsov.api.exceptions.services.LecturerServiceException;
import ru.spbstu.korobtsov.core.notification.email.EmailNotificationService;
import ru.spbstu.korobtsov.core.properties.EmailSendingProperties;
import ru.spbstu.korobtsov.core.repositories.LecturerRepository;
import ru.spbstu.korobtsov.core.services.InternalLecturerService;

import javax.transaction.Transactional;

@Slf4j
@Service
public class LecturerServiceImpl implements LecturerService, InternalLecturerService {

    private final LecturerRepository lecturerRepository;
    private final EmailNotificationService emailNotificationService;
    private final EmailSendingProperties emailSendingProperties;

    public LecturerServiceImpl(LecturerRepository lecturerRepository, EmailNotificationService emailNotificationService, EmailSendingProperties emailSendingProperties) {
        this.lecturerRepository = lecturerRepository;
        this.emailNotificationService = emailNotificationService;
        this.emailSendingProperties = emailSendingProperties;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Lecturer create(Lecturer lecturer) {
        log.debug("Creating {}", lecturer);
        try {
            var createdLecturer = lecturerRepository.save(lecturer);
            log.debug("Created {}", createdLecturer);
            return createdLecturer;
        } catch (Exception exception) {
            throw new LecturerServiceException("Error while creating %s, cause: %s".formatted(lecturer, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Iterable<Lecturer> readAll() {
        log.debug("Finding all");
        try {
            var lecturers = lecturerRepository.findAll();
            log.debug("Found {}", lecturers);
            return lecturers;
        } catch (Exception exception) {
            throw new LecturerServiceException("Error while reading all, cause: %s".formatted(exception.getMessage()), exception);
        }

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Lecturer readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var lecturer = lecturerRepository.findById(id)
                    .orElseThrow(() -> new LecturerNotFoundException("Lecturer with=id %s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, lecturer);
            return lecturer;
        } catch (Exception exception) {
            throw new LecturerServiceException("Error while read lecturer by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Lecturer update(Lecturer lecturer) {
        log.debug("Updating {}", lecturer);
        try {
            var updatedLecturer = lecturerRepository.save(lecturer);
            log.debug("Updated {}", updatedLecturer);
            return updatedLecturer;
        } catch (Exception exception) {
            throw new LecturerServiceException("Error while updating %s, cause: %s".formatted(lecturer, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {
            lecturerRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new LecturerServiceException("Error while deleting by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    public void sendAttention(Lecturer lecturer) {
        emailNotificationService.send(emailSendingProperties.getEmail(), lecturer.getEmail(), emailSendingProperties.getMessageForLecturer());
    }
}
