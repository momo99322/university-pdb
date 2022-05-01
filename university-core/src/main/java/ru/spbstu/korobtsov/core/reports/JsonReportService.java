package ru.spbstu.korobtsov.core.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.domain.Student;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Service
public class JsonReportService extends GenericReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonReportService.class);

    public JsonReportService(StudentService studentService, LectureService lectureService) {
        super(LOGGER, studentService, lectureService);
    }

    @Override
    public MimeType getType() {
        return APPLICATION_JSON;
    }

    @Override
    public String marshallStudent(Student student) {
        return null;
    }

    @Override
    public String marshallLecture(Lecture lecture) {
        return null;
    }
}
