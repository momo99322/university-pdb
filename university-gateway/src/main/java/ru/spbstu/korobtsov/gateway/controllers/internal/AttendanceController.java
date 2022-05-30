package ru.spbstu.korobtsov.gateway.controllers.internal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.AttendanceService;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Attendance;

import javax.validation.Valid;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final StudentService studentService;
    private final LectureService lectureService;

    public AttendanceController(AttendanceService attendanceService, StudentService studentService, LectureService lectureService) {
        this.attendanceService = attendanceService;
        this.studentService = studentService;
        this.lectureService = lectureService;
    }

    @GetMapping
    public String showAll(Model model) {
        var attendanceList = attendanceService.readAll();
        model.addAttribute("attendanceList", attendanceList);
        return "attendance/attendance";
    }

    @GetMapping(path = "/add")
    public String showCreateForm(Model model) {
        var students = studentService.readAll();
        var lectures = lectureService.readAll();
        model.addAttribute("students", students);
        model.addAttribute("lectures", lectures);
        return "attendance/add-attendance";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        var students = studentService.readAll();
        var lectures = lectureService.readAll();
        model.addAttribute("lectures", lectures);
        model.addAttribute("students", students);
        return "attendance/update-attendance";
    }

    @PostMapping
    public String addOne(@Valid Attendance attendance, BindingResult result) {
        if (result.hasErrors()) {
            return "attendance/add-attendance";
        }

        attendanceService.create(attendance);
        return "redirect:/attendance";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody @Valid Attendance attendance, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            attendance.setId(id);
            return "attendance/update-attendance";
        }

        attendanceService.update(attendance);
        return "redirect:/attendance";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        attendanceService.delete(id);
        return "redirect:/attendance";
    }
}
