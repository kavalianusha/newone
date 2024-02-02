package com.pathbreaker.pmp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ResourceSkills")
public class ResourceSkillsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private ResourceEntity resourceEntity;

    @Column(name = "PrimarySkills",length = 50)
    private String primarySkills;

    @Column(name = "SecondarySkills",length = 50)
    private String secondarySkills;

    @Column(name = "TertiarySkills",length = 50)
    private String terinarySkills;

    @Column(name = "SkillsPercentage",length = 50)
    private Integer skillsPercentage;
}
