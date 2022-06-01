package ru.spbstu.korobtsov.core.reports.impl;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.domain.Student;
import ru.spbstu.korobtsov.api.reports.ReportService;

public abstract class GenericReportService implements ReportService {

    protected static final FilterProvider STUDENT_FILTER_PROVIDER = createStudentFilterProvider();
    protected static final FilterProvider LECTURE_FILTER_PROVIDER = createLectureFilterProvider();
    private final Logger log;
    private final StudentService studentService;
    private final LectureService lectureService;

    protected GenericReportService(Logger logger, StudentService studentService, LectureService lectureService) {
        log = logger;
        this.studentService = studentService;
        this.lectureService = lectureService;
    }

    @Override
    public String generateReportByLectureName(String name) {
        log.debug("Generating report by lecture name={}", name);
        var lecture = lectureService.readOneByName(name);
        var report = marshallLecture(lecture);
        log.debug("Generated report by lecture name={}, report={}", name, report);
        return report;
    }

    @Override
    public String generateReportByStudentName(String name) {
        log.debug("Generating report by student name={}", name);
        var student = studentService.readOneByName(name);
        var report = marshallStudent(student);
        log.debug("Generated report by student name={}, report={}", name, report);
        return report;
    }

    public abstract String marshallStudent(Student student);

    public abstract String marshallLecture(Lecture lecture);

    private static FilterProvider createStudentFilterProvider() {
        var filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("studentFilter", SimpleBeanPropertyFilter.serializeAllExcept());
        filterProvider.addFilter("lectureFilter", SimpleBeanPropertyFilter.serializeAllExcept("attendance"));
        filterProvider.addFilter("attendanceFilter", SimpleBeanPropertyFilter.serializeAllExcept("student"));
        return filterProvider;
    }

    private static FilterProvider createLectureFilterProvider() {
        var filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("studentFilter", SimpleBeanPropertyFilter.serializeAllExcept("attendance"));
        filterProvider.addFilter("lectureFilter", SimpleBeanPropertyFilter.serializeAllExcept());
        filterProvider.addFilter("attendanceFilter", SimpleBeanPropertyFilter.serializeAllExcept("lecture"));
        return filterProvider;
    }

}
