package ru.spbstu.korobtsov.gateway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.LecturerService;
import ru.spbstu.korobtsov.api.domain.Lecturer;

@Controller
@RequestMapping("/lecturers")
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public String showAll(Model model) {
        var lecturers = lecturerService.readAll();
        model.addAttribute("lecturers", lecturers);
        return "lecturers/lecturers";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id,
                                 Model model) {

        var student = lecturerService.readOne(id);
        model.addAttribute("lecturer", student);
        return "lecturers/update-lecturer";
    }

    @PostMapping
    public String addOne(@RequestBody Lecturer lecturer,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "lecturers/add-lecturer";
        }

        lecturerService.create(lecturer);
        return "redirect:/lecturers";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Lecturer lecturer,
                         @PathVariable String id,
                         BindingResult result) {
        if (result.hasErrors()) {
            lecturer.setId(id);
            return "lecturers/update-lecturer";
        }

        lecturerService.update(lecturer);
        return "redirect:/lecturers";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        lecturerService.delete(id);
        return "redirect:/lecturers";
    }
}