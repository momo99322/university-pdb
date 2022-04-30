package ru.spbstu.korobtsov.gateway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.StudentService;
import ru.spbstu.korobtsov.api.domain.Student;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String showAll(Model model) {
        var students = studentService.readAll();
        model.addAttribute("students", students);
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
    public String addOne(@RequestBody Student student,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "students/add-student";
        }

        studentService.create(student);
        return "redirect:/students";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Student student,
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
}
