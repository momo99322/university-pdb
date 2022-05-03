package ru.spbstu.korobtsov.core.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.domain.Student;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

import static org.springframework.util.MimeTypeUtils.APPLICATION_XML;

@Slf4j
@Service
public class XmlReportService extends GenericReportService {

    private final StringWriter sw;

    public XmlReportService(StudentService studentService, LectureService lectureService) {
        super(log, studentService, lectureService);
        this.sw = new StringWriter();
    }

    @Override
    public MimeType getType() {
        return APPLICATION_XML;
    }

    @Override
    public String marshallStudent(Student student) {
        JAXB.marshal(student, sw);
        return sw.toString();
    }

    @Override
    public String marshallLecture(Lecture lecture) {
        JAXB.marshal(lecture, sw);
        return sw.toString();
    }
}
