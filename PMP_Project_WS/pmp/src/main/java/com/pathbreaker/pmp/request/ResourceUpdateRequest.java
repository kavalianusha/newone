package com.pathbreaker.pmp.request;

import lombok.Data;

@Data
public class ResourceUpdateRequest {

    private String employeeName;

    private Integer experience;

    private String designation;

    private String technology;

    private String department;

    private String manager;

    private String project;

    private ResourceSkillsUpdateRequest resourceSkillsUpdateRequest;
}
