package com.pathbreaker.pmp.response;

import com.pathbreaker.pmp.entity.RevenueProductEntity;
import com.pathbreaker.pmp.entity.RevenueProjectEntity;
import com.pathbreaker.pmp.entity.RevenueSupportEntity;
import com.pathbreaker.pmp.entity.RevenueTraineeEntity;
import lombok.Data;

import java.util.List;

@Data
public class RevenueResponse {
    private String projectName;
    private String techStack;
    private String month;
    private String revenueType;
    private String source;
    private Double payment1;
    private Double payment2;
    private Double payment3;
    private Double payment4;

    private RevenueProductResponse revenueProducts;

    private RevenueProjectResponse revenueProjects;

    private RevenueSupportResponse revenueSupports;

    private RevenueTraineeResponse revenueTrainees;
}
