package ru.spbstu.korobtsov.gateway.controllers.internal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Student;
import ru.spbstu.korobtsov.api.reports.ReportServiceManager;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final ReportServiceManager reportServiceManager;

    public StudentController(StudentService studentService, ReportServiceManager reportServiceManager) {
        this.studentService = studentService;
        this.reportServiceManager = reportServiceManager;
    }

    @GetMapping
    public String showAll(Model model) {
        var students = studentService.readAll();
        model.addAttribute("students", students);
        model.addAttribute("formats", reportServiceManager.getSuppoertedFormats());
        return "students/students";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id,
                                 Model model) {

        var student = studentService.readOne(id);
        model.addAttribute("student", student);
        return "students/update-student";
    }

    @PostMapping
    public String addOne(@Valid Student student,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "students/add-student";
        }

        studentService.create(student);
        return "redirect:/students";
    }

    @GetMapping(path = "/add")
    public String showCreateForm() {
        return "students/add-student";
    }

    @PutMapping("/{id}")
    public String update(@Valid Student student,
                         @PathVariable String id,
                         BindingResult result) {
        if (result.hasErrors()) {
            student.setId(id);
            return "students/update-student";
        }

        studentService.update(student);
        return "redirect:/students";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        studentService.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/report")
    public String getReportByStudentName(@RequestParam("name") String name,
                                         @RequestParam("type") MimeType type,
                                         Model model) {
        var reportService = reportServiceManager.getReportService(type);
        var report = reportService.generateReportByStudentName(name);
        model.addAttribute("report", report);
        model.addAttribute("name", name);
        return "students/student-report";
    }
}
