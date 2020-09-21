package ru.sibsutis.kitosina.TestExample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sibsutis.kitosina.TestExample.entity.Group;
import ru.sibsutis.kitosina.TestExample.service.GroupService;

@RestController
@RequestMapping("/groups")
@Slf4j
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Group group) {
        return ResponseEntity.ok(groupService.save(group));
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @GetMapping("/by/{groupName}")
    public ResponseEntity findByGroupName(@PathVariable String groupName) {
        return ResponseEntity.ok(groupService.findByGroupName(groupName));
    }

    @PostMapping("/update")
    //String quantityStudents, String group_name
    //20-02
    public void setGroupInfoQuantityStudents(@RequestBody Group group) {
        log.warn(group.toString());
        groupService.setGroupInfoQuantityStudents(group.getQuantityStudents(), group.getGroupName());
    }
}
