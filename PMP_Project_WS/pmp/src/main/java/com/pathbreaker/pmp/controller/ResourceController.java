package com.pathbreaker.pmp.controller;

import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.request.ResourceRequest;
import com.pathbreaker.pmp.request.ResourceSkillsUpdateRequest;
import com.pathbreaker.pmp.request.ResourceUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/employee")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/registration")
    public ResponseEntity<?> createResource(@RequestBody ResourceRequest resourceRequest){
          return  resourceService.createResource(resourceRequest);
    }
    @GetMapping("/all")
    public List<ResourceResponse> getAllResource(){
        return resourceService.getAllResources();
    }

    @GetMapping("/{employeeId}")
    public  ResourceResponse getEmployeeById(@PathVariable String employeeId){
        return resourceService.getAllResourceById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateResource(@PathVariable String employeeId,@RequestBody ResourceUpdateRequest resourceUpdateRequest){
        return resourceService.updateResourceById(employeeId,resourceUpdateRequest);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteResource(@PathVariable String employeeId){
        return resourceService.deleteResourceById(employeeId);
    }
}
