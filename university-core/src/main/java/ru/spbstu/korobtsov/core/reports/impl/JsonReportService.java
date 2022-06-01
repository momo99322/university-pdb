package ru.spbstu.korobtsov.core.reports.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.domain.Student;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Slf4j
@Service
public class JsonReportService extends GenericReportService {

    private final ObjectMapper objectMapper;

    public JsonReportService(StudentService studentService, LectureService lectureService, ObjectMapper objectMapper) {
        super(log, studentService, lectureService);
        this.objectMapper = objectMapper;
    }

    @Override
    public MimeType getType() {
        return APPLICATION_JSON;
    }

    @Override
    @SneakyThrows
    public String marshallStudent(Student student) {
        return objectMapper
                .writer(STUDENT_FILTER_PROVIDER)
                .withDefaultPrettyPrinter()
                .writeValueAsString(student);
    }

    @Override
    @SneakyThrows
    public String marshallLecture(Lecture lecture) {
        return objectMapper
                .writer(LECTURE_FILTER_PROVIDER)
                .withDefaultPrettyPrinter()
                .writeValueAsString(lecture);
    }
}
