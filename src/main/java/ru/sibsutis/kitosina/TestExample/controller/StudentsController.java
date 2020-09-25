package ru.sibsutis.kitosina.TestExample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sibsutis.kitosina.TestExample.entity.GroupStudents;
import ru.sibsutis.kitosina.TestExample.entity.Students;
import ru.sibsutis.kitosina.TestExample.service.StudentsService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentsController {

    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody GroupStudents groupStudents) throws ParseException {
        log.warn(groupStudents.toString());
        return ResponseEntity.ok(studentsService.save(groupStudents));
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(studentsService.findAll());
    }

    @GetMapping("/by/{groupName}")
    public ResponseEntity findByGroupGroupName(@PathVariable String groupName) {
        return ResponseEntity.ok(studentsService.findByGroupGroupName(groupName));
    }

    @DeleteMapping("/{firstName}/{lastName}/{groupName}")
    public void deleteByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String groupName) {
        studentsService.deleteByFirstNameAndLastName(firstName, lastName, groupName);
    }

}
