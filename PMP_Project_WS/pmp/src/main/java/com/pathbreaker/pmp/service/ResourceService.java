package com.pathbreaker.pmp.service;


import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.request.ResourceRequest;
import com.pathbreaker.pmp.request.ResourceSkillsUpdateRequest;
import com.pathbreaker.pmp.request.ResourceUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.ResourceSkillsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResourceService {
    ResponseEntity<?> createResource(ResourceRequest resourceRequest);

    List<ResourceResponse> getAllResources();

    ResourceResponse getAllResourceById(String employeeId);

    ResponseEntity<?> updateResourceById(String employeeId, ResourceUpdateRequest resourceUpdateRequest);

    ResponseEntity<?> deleteResourceById(String employeId);
}
