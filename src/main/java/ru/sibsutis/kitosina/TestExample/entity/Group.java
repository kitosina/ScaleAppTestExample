package ru.sibsutis.kitosina.TestExample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_info")
public class Group {

    @Id
    private String groupName;

    @Column
    private Integer quantityStudents;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Students> students;
}
