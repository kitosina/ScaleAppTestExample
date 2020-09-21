package ru.sibsutis.kitosina.TestExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibsutis.kitosina.TestExample.entity.Students;
import ru.sibsutis.kitosina.TestExample.repository.StudentsRepository;

import java.util.List;

@Service
public class StudentsService {

    private StudentsRepository studentsRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Students save(Students students) {
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
