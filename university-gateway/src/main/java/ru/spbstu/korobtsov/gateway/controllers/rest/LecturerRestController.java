package ru.spbstu.korobtsov.gateway.controllers.rest;

import org.springframework.web.bind.annotation.*;
import ru.spbstu.korobtsov.api.LecturerService;
import ru.spbstu.korobtsov.api.domain.Lecturer;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/lecturers")
public class LecturerRestController {

    private final LecturerService lecturerService;

    public LecturerRestController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public Iterable<Lecturer> showAll() {
        return lecturerService.readAll();
    }

    @GetMapping(path = "/{id}")
    public Lecturer showUpdateForm(@PathVariable String id) {
        return lecturerService.readOne(id);
    }

    @PostMapping
    public Lecturer addOne(@RequestBody @Valid Lecturer lecturer) {
        return lecturerService.create(lecturer);
    }

    @PutMapping("/{id}")
    public Lecturer update(@RequestBody @Valid Lecturer lecturer,
                           @PathVariable String id) {
        lecturer.setId(id);
        return lecturerService.update(lecturer);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        lecturerService.delete(id);
    }
}
