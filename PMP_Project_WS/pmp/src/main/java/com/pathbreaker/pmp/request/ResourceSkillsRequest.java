package com.pathbreaker.pmp.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.response.ResourceResponse;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ResourceSkillsRequest {

    private Long id;
    private String primarySkills;
    private String secondarySkills;
    private String terinarySkills;
    private Integer skillsPercentage;
    private ResourceRequest resourceRequest;
}
