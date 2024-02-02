package com.pathbreaker.pmp.request;

import com.pathbreaker.pmp.entity.RevenueProductEntity;
import com.pathbreaker.pmp.entity.RevenueProjectEntity;
import com.pathbreaker.pmp.entity.RevenueSupportEntity;
import com.pathbreaker.pmp.entity.RevenueTraineeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class RevenueRequest {

    private String projectName;
    private String techStack;
    private String month;
    private String revenueType;
    private String source;
    private Double payment1;
    private Double payment2;
    private Double payment3;
    private Double payment4;

    private RevenueProductEntity revenueProducts;

    private RevenueProjectEntity revenueProjects;

    private RevenueSupportEntity revenueSupports;

    private RevenueTraineeEntity revenueTrainees;
}
