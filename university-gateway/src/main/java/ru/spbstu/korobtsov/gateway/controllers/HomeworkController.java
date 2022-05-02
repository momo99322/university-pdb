package ru.spbstu.korobtsov.gateway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.domain.Homework;

import javax.validation.Valid;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping
    public String showAll(Model model) {
        var homework = homeworkService.readAll();
        model.addAttribute("homework", homework);
        return "homework/homework";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id,
                                 Model model) {

        var student = homeworkService.readOne(id);
        model.addAttribute("homework", student);
        return "homework/update-homework";
    }

    @PostMapping
    public String addOne(@RequestBody @Valid Homework homework,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "homework/add-homework";
        }

        homeworkService.create(homework);
        return "redirect:/homework";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody @Valid Homework homework,
                         @PathVariable String id,
                         BindingResult result) {
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
