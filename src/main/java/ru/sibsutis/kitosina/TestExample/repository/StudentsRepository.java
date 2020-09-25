package ru.sibsutis.kitosina.TestExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibsutis.kitosina.TestExample.entity.Group;
import ru.sibsutis.kitosina.TestExample.entity.Students;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

    List<Students> findByGroup_GroupName(String groupName);

    @Transactional
    @Modifying
    @Query(value = "delete from students where first_name = ?1 AND last_name = ?2 AND group_name = ?3", nativeQuery = true)
    void deleteByFirstNameAndLastName(@Param("first_name") String firstName, @Param("last_name") String lastName, @Param("group_name") String groupName);

}
