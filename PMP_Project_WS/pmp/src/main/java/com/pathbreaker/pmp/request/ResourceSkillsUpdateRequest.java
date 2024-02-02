package com.pathbreaker.pmp.request;

import lombok.Data;

@Data
public class ResourceSkillsUpdateRequest {

    private String primarySkills;
    private String secondarySkills;
    private String terinarySkills;
    private Integer skillsPercentage;

}
