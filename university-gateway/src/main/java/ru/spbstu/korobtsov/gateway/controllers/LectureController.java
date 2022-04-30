package ru.spbstu.korobtsov.gateway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.LectureService;
import ru.spbstu.korobtsov.api.domain.Lecture;

@Controller
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping
    public String showAll(Model model) {
        var lectures = lectureService.readAll();
        model.addAttribute("lectures", lectures);
        return "lectures/lectures";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id,
                                 Model model) {

        var student = lectureService.readOne(id);
        model.addAttribute("lecture", student);
        return "lectures/update-lecture";
    }

    @PostMapping
    public String addOne(@RequestBody Lecture lecture,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "lectures/add-lecture";
        }

        lectureService.create(lecture);
        return "redirect:/lectures";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Lecture lecture,
                         @PathVariable String id,
                         BindingResult result) {
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
}
