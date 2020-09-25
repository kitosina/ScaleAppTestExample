package ru.sibsutis.kitosina.TestExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibsutis.kitosina.TestExample.entity.Group;

import javax.transaction.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    @Query(value = "select quantity_students from group_info where group_name = ?1", nativeQuery = true)
    Integer getQuantityStudents(@Param("group_name") String groupName);

    Group findByGroupName(String groupName);

    @Transactional
    @Modifying
    @Query(value = "update group_info set quantity_students = ?1 where group_name = ?2", nativeQuery = true)
    void setGroupInfoQuantityStudents(@Param("quantity_students") Integer quantityStudents,@Param("group_name") String group_name);

}
