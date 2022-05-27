package ru.spbstu.korobtsov.gateway.controllers.internal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.LecturerService;
import ru.spbstu.korobtsov.api.domain.Lecturer;

import javax.validation.Valid;

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

    @GetMapping(path = "/add")
    public String showCreateForm(Model model) {
        return "lecturers/add-lecturer";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id,
                                 Model model) {

        var student = lecturerService.readOne(id);
        model.addAttribute("lecturer", student);
        return "lecturers/update-lecturer";
    }

    @PostMapping
    public String addOne(@Valid Lecturer lecturer,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "lecturers/add-lecturer";
        }

        lecturerService.create(lecturer);
        return "redirect:/lecturers";
    }

    @PutMapping("/{id}")
    public String update(@Valid Lecturer lecturer,
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
