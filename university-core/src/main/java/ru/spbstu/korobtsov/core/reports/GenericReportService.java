package ru.spbstu.korobtsov.core.reports;

import org.slf4j.Logger;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.ReportService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.domain.Student;

public abstract class GenericReportService implements ReportService {

    private final Logger LOGGER;
    private final StudentService studentService;
    private final LectureService lectureService;

    protected GenericReportService(Logger logger, StudentService studentService, LectureService lectureService) {
        LOGGER = logger;
        this.studentService = studentService;
        this.lectureService = lectureService;
    }

    @Override
    public String generateReportByLectureName(String name) {
        LOGGER.debug("Generating report by lecture name={}", name);
        var lecture = lectureService.readOneByName(name);
        var report = marshallLecture(lecture);
        LOGGER.debug("Generated report by lecture name={}, report={}", name, report);
        return report;
    }

    @Override
    public String generateReportByStudentName(String name) {
        LOGGER.debug("Generating report by student name={}", name);
        var student = studentService.readOneByName(name);
        var report = marshallStudent(student);
        LOGGER.debug("Generated report by student name={}, report={}", name, report);
        return report;
    }

    public abstract String marshallStudent(Student student);

    public abstract String marshallLecture(Lecture lecture);

}
