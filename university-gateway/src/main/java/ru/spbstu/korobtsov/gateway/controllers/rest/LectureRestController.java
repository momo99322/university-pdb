package ru.spbstu.korobtsov.gateway.controllers.rest;

import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.ReportService;
import ru.spbstu.korobtsov.api.domain.Lecture;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1/lectures")
public class LectureRestController {

    private final LectureService lectureService;
    private final Map<MimeType, ReportService> reportServiceMap;

    public LectureRestController(LectureService lectureService, Map<MimeType, ReportService> reportServiceMap) {
        this.lectureService = lectureService;
        this.reportServiceMap = reportServiceMap;
    }

    @GetMapping
    public Iterable<Lecture> showAll() {
        return lectureService.readAll();
    }

    @GetMapping(path = "/{id}")
    public Lecture showUpdateForm(@PathVariable String id) {
        return lectureService.readOne(id);
    }

    @PostMapping
    public Lecture addOne(@RequestBody @Valid Lecture lecture) {
        return lectureService.create(lecture);
    }

    @PutMapping("/{id}")
    public Lecture update(@RequestBody @Valid Lecture lecture,
                          @PathVariable String id) {
        lecture.setId(id);
        return lectureService.update(lecture);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        lectureService.delete(id);
    }

    @GetMapping("/report")
    public String getReportByLectureName(@RequestParam("name") String name,
                                         @RequestParam("type") MimeType type) {
        var reportService = reportServiceMap.get(type);
        return reportService.generateReportByLectureName(name);
    }
}
