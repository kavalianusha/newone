package com.pathbreaker.pmp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingEntity {

    @Id
    private String cnpId;

    @Column(length = 30)
    private String projectName;

    @Column(length = 10)
    private String month;

    @Column(length = 30)
    private String revenueType;

    @Column(length = 30)
    private String source;

    @Column(length = 5)
    private Integer completion;

    @Column(length = 30)
    private String resourceAssigned;

    @Column(length = 10)
    private Integer revenue;

    @Column(length = 10)
    private String date;

}