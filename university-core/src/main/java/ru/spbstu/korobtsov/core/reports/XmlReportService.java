package ru.spbstu.korobtsov.core.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.domain.Student;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

import static org.springframework.util.MimeTypeUtils.APPLICATION_XML;

@Service
public class XmlReportService extends GenericReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlReportService.class);
    private final StringWriter sw;

    public XmlReportService(StudentService studentService, LectureService lectureService) {
        super(LOGGER, studentService, lectureService);
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
