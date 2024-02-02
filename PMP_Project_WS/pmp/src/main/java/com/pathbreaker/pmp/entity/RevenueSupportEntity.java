package com.pathbreaker.pmp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "RevenueSupport")
public class RevenueSupportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private Double supportRevenue;

    @OneToOne()
    @JoinColumn(name = "projectName")
    private RevenueEntity revenueEntity;
}
