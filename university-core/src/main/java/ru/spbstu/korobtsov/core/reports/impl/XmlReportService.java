package ru.spbstu.korobtsov.core.reports.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.domain.Student;

import static org.springframework.util.MimeTypeUtils.APPLICATION_XML;

@Slf4j
@Service
public class XmlReportService extends GenericReportService {

    private final XmlMapper xmlMapper;

    public XmlReportService(StudentService studentService, LectureService lectureService, XmlMapper xmlMapper) {
        super(log, studentService, lectureService);
        this.xmlMapper = xmlMapper;
    }

    @Override
    public MimeType getType() {
        return APPLICATION_XML;
    }

    @Override
    @SneakyThrows
    public String marshallStudent(Student student) {
        return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
    }

    @Override
    @SneakyThrows
    public String marshallLecture(Lecture lecture) {
        return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lecture);
    }
}
