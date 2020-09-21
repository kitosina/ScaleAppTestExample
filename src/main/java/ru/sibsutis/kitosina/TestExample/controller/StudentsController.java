package ru.sibsutis.kitosina.TestExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sibsutis.kitosina.TestExample.entity.Students;
import ru.sibsutis.kitosina.TestExample.service.StudentsService;

import java.util.Objects;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    public ResponseEntity save(Students students) {
        return ResponseEntity.ok(studentsService.save(students));
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(studentsService.findAll());
    }

    @GetMapping("/by/{groupName}")
    public ResponseEntity findByGroupGroupName(@PathVariable String groupName) {
        return ResponseEntity.ok(studentsService.findByGroupGroupName(groupName));
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public void deleteByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        studentsService.deleteByFirstNameAndLastName(firstName, lastName);
    }

}
