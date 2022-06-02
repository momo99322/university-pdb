package ru.spbstu.korobtsov.core.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.api.AttendanceService;
import ru.spbstu.korobtsov.api.domain.Attendance;
import ru.spbstu.korobtsov.api.exceptions.notfound.AttendanceNotFoundException;
import ru.spbstu.korobtsov.api.exceptions.services.AttendanceServiceException;
import ru.spbstu.korobtsov.core.repositories.AttendanceRepository;
import ru.spbstu.korobtsov.core.services.InternalLecturerService;
import ru.spbstu.korobtsov.core.services.InternalStudentService;

import javax.transaction.Transactional;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final InternalStudentService studentService;

    private final InternalLecturerService lecturerService;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 InternalStudentService studentService,
                                 InternalLecturerService lecturerService) {
        this.attendanceRepository = attendanceRepository;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Attendance create(Attendance attendance) {
        log.debug("Creating {}", attendance);
        try {
            var createdAttendance = attendanceRepository.save(attendance);
            log.debug("Created {}", createdAttendance);

            var student = createdAttendance.getStudent();
            var lecturer = createdAttendance.getLecture().getLecturer();

            if (!createdAttendance.isAttendance() && studentService.checkStudentAttendance(student)) {

                studentService.sendAttention(student);
                lecturerService.sendAttention(lecturer);
            }

            return createdAttendance;
        } catch (Exception exception) {
            throw new AttendanceServiceException("Error while creating %s, cause: %s".formatted(attendance, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Iterable<Attendance> readAll() {
        log.debug("Finding all");
        try {
            var attendance = attendanceRepository.findAll();
            log.debug("Found {}", attendance);
            return attendance;
        } catch (Exception exception) {
            throw new AttendanceServiceException("Error while reading all, cause: %s".formatted(exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Attendance readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var attendance = attendanceRepository.findById(id).orElseThrow(() -> new AttendanceNotFoundException("Attendance with=id %s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, attendance);
            return attendance;
        } catch (Exception exception) {
            throw new AttendanceServiceException("Error while read attendance by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Attendance update(Attendance attendance) {
        log.debug("Updating {}", attendance);
        try {
            var updatedAttendance = attendanceRepository.save(attendance);
            log.debug("Updated {}", updatedAttendance);
            return updatedAttendance;
        } catch (Exception exception) {
            throw new AttendanceServiceException("Error while updating %s, cause: %s".formatted(attendance, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {
            attendanceRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new AttendanceServiceException("Error while deleting by id=%s, cause: %s".formatted(id, exception.getMessage()), exception);
        }
    }

}
