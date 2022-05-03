package ru.spbstu.korobtsov.gateway.controllers.rest;

import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.ReportService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Student;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1/students")
public class StudentRestController {

    private final StudentService studentService;
    private final Map<MimeType, ReportService> reportServiceMap;

    public StudentRestController(StudentService studentService, Map<MimeType, ReportService> reportServiceMap) {
        this.studentService = studentService;
        this.reportServiceMap = reportServiceMap;
    }

    @GetMapping
    public Iterable<Student> showAll() {
        return studentService.readAll();
    }

    @GetMapping(path = "/{id}")
    public Student showUpdateForm(@PathVariable String id) {
        return studentService.readOne(id);
    }

    @PostMapping
    public Student addOne(@RequestBody @Valid Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@RequestBody @Valid Student student,
                          @PathVariable String id) {
        student.setId(id);
        return studentService.update(student);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        studentService.delete(id);
    }

    @GetMapping("/report")
    public String getReportByStudentName(@RequestParam("name") String name,
                                         @RequestParam("type") MimeType type) {
        var reportService = reportServiceMap.get(type);
        return reportService.generateReportByStudentName(name);
    }
}
