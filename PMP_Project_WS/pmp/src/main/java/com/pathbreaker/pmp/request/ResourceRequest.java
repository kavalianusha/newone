package com.pathbreaker.pmp.request;

import com.pathbreaker.pmp.entity.ResourceSkillsEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class ResourceRequest {

    private String employeeId;

    private String employeeName;

    private Integer experience;

    private String designation;

    private String technology;

    private String department;

    private String manager;

    private String project;

    private ResourceSkillsRequest resourceSkillsRequest;
}
