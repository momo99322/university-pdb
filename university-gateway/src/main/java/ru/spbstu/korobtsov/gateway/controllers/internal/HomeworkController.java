package ru.spbstu.korobtsov.gateway.controllers.internal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Homework;

import javax.validation.Valid;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;
    private final LectureService lectureService;
    private final StudentService studentService;

    public HomeworkController(HomeworkService homeworkService, LectureService lectureService, StudentService studentService) {
        this.homeworkService = homeworkService;
        this.lectureService = lectureService;
        this.studentService = studentService;
    }

    @GetMapping
    public String showAll(Model model) {
        var homeworks = homeworkService.readAll();
        model.addAttribute("homeworks", homeworks);
        return "homework/homework";
    }

    @GetMapping(path = "/add")
    public String showCreateForm(Model model) {
        var lectures = lectureService.readAll();
        var students = studentService.readAll();
        model.addAttribute("students", students);
        model.addAttribute("lectures", lectures);
        return "homework/add-homework";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        var homework = homeworkService.readOne(id);
        var lectures = lectureService.readAll();
        var students = studentService.readAll();
        model.addAttribute("lectures", lectures);
        model.addAttribute("students", students);
        model.addAttribute("homework", homework);
        return "homework/update-homework";
    }

    @PostMapping
    public String addOne(@Valid Homework homework, BindingResult result) {
        if (result.hasErrors()) {
            return "homework/add-homework";
        }

        homeworkService.create(homework);
        return "redirect:/homework";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody @Valid Homework homework, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            homework.setId(id);
            return "homework/update-homework";
        }

        homeworkService.update(homework);
        return "redirect:/homework";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        homeworkService.delete(id);
        return "redirect:/homework";
    }
}
