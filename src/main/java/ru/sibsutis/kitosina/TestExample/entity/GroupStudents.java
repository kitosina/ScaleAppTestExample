package ru.sibsutis.kitosina.TestExample.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class GroupStudents {

    private Date date;
    private String firstName;
    private String middleName;
    private String lastName;
    private String groupName;
//    private Integer quantityStudents;

}
