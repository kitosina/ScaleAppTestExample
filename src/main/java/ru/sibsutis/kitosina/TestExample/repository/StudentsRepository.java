package ru.sibsutis.kitosina.TestExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibsutis.kitosina.TestExample.entity.Students;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

    List<Students> findByGroup_GroupName(String groupName);

    @Transactional
    void deleteByFirstNameAndLastName(String firstName, String lastName);

}
