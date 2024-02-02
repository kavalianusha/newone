package com.pathbreaker.pmp.response;

import com.pathbreaker.pmp.request.ResourceSkillsRequest;
import lombok.Data;

@Data
public class ResourceResponse {

    private String employeeId;

    private String employeeName;

    private Integer experience;

    private String designation;

    private String technology;

    private String department;

    private String manager;

    private String project;

    private ResourceSkillsResponse resourceSkillsResponse;

}
