package com.pathbreaker.pmp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Resource")
public class   ResourceEntity {

    @Id
    @Column(length = 10)
    private String employeeId;

    @Column(name = "EmployeeName",length = 50)
    private String employeeName;

    @Column(name = "Experience",length = 5)
    private Integer experience;

    @Column(name = "Designation",length = 50)
    private String designation;

    @Column(name = "Technology",length = 40)
    private String technology;

    @Column(name = "Department",length = 40)
    private String department;

    @Column(name = "Manager",length = 20)
    private String manager;

    @Column(name = "Project",length = 50)
    private String project;

    @OneToOne(mappedBy = "resourceEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private ResourceSkillsEntity resourceSkillsEntity;

}
