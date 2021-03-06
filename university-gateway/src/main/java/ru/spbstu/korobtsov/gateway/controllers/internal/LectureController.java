package ru.spbstu.korobtsov.gateway.controllers.internal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.LecturerService;
import ru.spbstu.korobtsov.api.domain.Lecture;
import ru.spbstu.korobtsov.api.reports.ReportServiceManager;

import javax.validation.Valid;

@Controller
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;
    private final LecturerService lecturerService;
    private final ReportServiceManager reportServiceManager;

    public LectureController(LectureService lectureService, LecturerService lecturerService, ReportServiceManager reportServiceManager) {
        this.lectureService = lectureService;
        this.reportServiceManager = reportServiceManager;
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public String showAll(Model model) {
        var lectures = lectureService.readAll();
        model.addAttribute("lectures", lectures);
        model.addAttribute("formats", reportServiceManager.getSuppoertedFormats());
        return "lectures/lectures";
    }

    @GetMapping(path = "/add")
    public String showCreateForm(Model model) {
        var lecturers = lecturerService.readAll();
        model.addAttribute("lecturers", lecturers);
        return "lectures/add-lecture";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        var student = lectureService.readOne(id);
        var lecturers = lecturerService.readAll();
        model.addAttribute("lecture", student);
        model.addAttribute("lecturers", lecturers);
        return "lectures/update-lecture";
    }

    @PostMapping
    public String addOne(@Valid Lecture lecture) {
        lectureService.create(lecture);
        return "redirect:/lectures";
    }

    @PutMapping("/{id}")
    public String update(@Valid Lecture lecture, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            lecture.setId(id);
            return "lectures/update-lecture";
        }

        lectureService.update(lecture);
        return "redirect:/lectures";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        lectureService.delete(id);
        return "redirect:/lectures";
    }

    @GetMapping("/report")
    public String getReportByLectureName(@RequestParam("name") String name,
                                         @RequestParam("type") MimeType type,
                                         Model model) {
        var reportService = reportServiceManager.getReportService(type);
        var report = reportService.generateReportByLectureName(name);
        model.addAttribute("report", report);
        model.addAttribute("name", name);
        return "lectures/lecture-report";
    }
}
