package ru.sibsutis.kitosina.TestExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.sibsutis.kitosina.TestExample.entity.Group;
import ru.sibsutis.kitosina.TestExample.repository.GroupRepository;
import ru.sibsutis.kitosina.TestExample.repository.StudentsRepository;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Integer getQuantityStudents(String groupName) {
        return groupRepository.getQuantityStudents(groupName);
    }

    public void setGroupInfoQuantityStudents(Integer quantityStudents, String group_name) {
        groupRepository.setGroupInfoQuantityStudents(quantityStudents, group_name);
    }
}
