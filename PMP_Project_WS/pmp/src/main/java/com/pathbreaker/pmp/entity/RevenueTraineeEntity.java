package com.pathbreaker.pmp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "RevenueTraining")
public class RevenueTraineeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private Double traineeRevenue;

    @OneToOne()
    @JoinColumn(name = "projectName")
    private RevenueEntity revenueEntity;
}
