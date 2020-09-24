package ru.sibsutis.kitosina.TestExample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibsutis.kitosina.TestExample.entity.Group;
import ru.sibsutis.kitosina.TestExample.entity.GroupStudents;
import ru.sibsutis.kitosina.TestExample.entity.Students;
import ru.sibsutis.kitosina.TestExample.repository.StudentsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StudentsService {

    private final StudentsRepository studentsRepository;
    private final SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Students save(GroupStudents groupStudents) {
        Group group = new Group(groupStudents.getGroupName(), groupStudents.getQuantityStudents(), null);
        Students students = new Students(
                null, groupStudents.getDate(),
                groupStudents.getFirstName(), groupStudents.getMiddleName(),
                groupStudents.getLastName(), group);

        return studentsRepository.save(students);
    }

    public List<Students> findAll() {
        return studentsRepository.findAll();
    }

    public List<Students> findByGroupGroupName(String groupName) {
        return studentsRepository.findByGroup_GroupName(groupName);
    }

    public void deleteByFirstNameAndLastName(String firstName,String lastName) {
        studentsRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

}
