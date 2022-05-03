package ru.spbstu.korobtsov.gateway.controllers.rest;

import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.HomeworkService;
import ru.spbstu.korobtsov.api.domain.Homework;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/homework")
public class HomeworkRestController {

    private final HomeworkService homeworkService;

    public HomeworkRestController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping
    public Iterable<Homework> showAll() {
        return homeworkService.readAll();
    }

    @GetMapping(path = "/{id}")
    public Homework showUpdateForm(@PathVariable String id) {
        return homeworkService.readOne(id);
    }

    @PostMapping
    public Homework addOne(@RequestBody @Valid Homework homework) {
        return homeworkService.create(homework);
    }

    @PutMapping("/{id}")
    public Homework update(@RequestBody @Valid Homework homework,
                           @PathVariable String id) {
        homework.setId(id);
        return homeworkService.update(homework);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        homeworkService.delete(id);
    }
}
