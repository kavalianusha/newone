package com.pathbreaker.pmp.response;

import com.pathbreaker.pmp.request.ResourceRequest;
import lombok.Data;

@Data
public class ResourceSkillsResponse {

    private Long id;
    private String primarySkills;
    private String secondarySkills;
    private String terinarySkills;
    private Integer skillsPercentage;
    private ResourceResponse resourceResponse;

}
