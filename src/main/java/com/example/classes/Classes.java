//tag::all[]
//tag::allButValidation[]
package com.example.classes;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "class")
public class Classes implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="required field")
    private String name;

    @NotEmpty(message="required field")
    @Column(name = "teacher_id")
    private Long teacherId;

    @NotEmpty(message="required field")
    @Column(name = "group_id")
    private Long groupId;

}
