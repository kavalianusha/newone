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
@Table(name = "Revenue")
public class RevenueEntity {

    @Id
    @Column(length = 40)
    private String projectName;

    @Column(length = 40)
    private String techStack;

    @Column(length = 40)
    private String month;

    @Column(length = 40)
    private String revenueType;

    @Column(length = 40)
    private String source;

    @Column(length = 10)
    private Double payment1;

    @Column(length = 40)
    private Double payment2;

    @Column(length = 40)
    private Double payment3;

    @Column(length = 40)
    private Double payment4;

    @OneToOne(mappedBy = "revenueEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private RevenueProductEntity revenueProductEntity;

    @OneToOne(mappedBy = "revenueEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private RevenueProjectEntity revenueProjectEntity;

    @OneToOne(mappedBy = "revenueEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private RevenueSupportEntity revenueSupportEntity;

    @OneToOne(mappedBy = "revenueEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private RevenueTraineeEntity revenueTraineeEntity;
}
