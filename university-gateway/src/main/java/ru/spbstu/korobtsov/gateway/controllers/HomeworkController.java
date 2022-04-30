package ru.spbstu.korobtsov.gateway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.domain.Homework;

@Controller
@RequestMapping("/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping
    public String showAll(Model model) {
        var homeworks = homeworkService.readAll();
        model.addAttribute("homeworks", homeworks);
        return "homeworks/homeworks";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id,
                                 Model model) {

        var student = homeworkService.readOne(id);
        model.addAttribute("homework", student);
        return "homeworks/update-homework";
    }

    @PostMapping
    public String addOne(@RequestBody Homework homework,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "homeworks/add-homework";
        }

        homeworkService.create(homework);
        return "redirect:/homeworks";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Homework homework,
                         @PathVariable String id,
                         BindingResult result) {
        if (result.hasErrors()) {
            homework.setId(id);
            return "homeworks/update-homework";
        }

        homeworkService.update(homework);
        return "redirect:/homeworks";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        homeworkService.delete(id);
        return "redirect:/homeworks";
    }
}