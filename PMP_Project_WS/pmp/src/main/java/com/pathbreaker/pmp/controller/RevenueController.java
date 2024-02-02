package com.pathbreaker.pmp.controller;

import com.pathbreaker.pmp.entity.RevenueEntity;
import com.pathbreaker.pmp.request.ResourceRequest;
import com.pathbreaker.pmp.request.ResourceUpdateRequest;
import com.pathbreaker.pmp.request.RevenueRequest;
import com.pathbreaker.pmp.request.RevenueUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.RevenueResponse;
import com.pathbreaker.pmp.service.RevenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @PostMapping("/registration")
    public ResponseEntity<?> createRevenue(@RequestBody RevenueRequest revenueRequest){
        return  revenueService.createRevenue(revenueRequest);
    }

    @GetMapping("/all")
    public List<RevenueResponse> getAllRevenue(){
        return revenueService.getAllRevenues();
    }
    @GetMapping("/{projectName}")
    public  RevenueResponse getRevenueByProjectName(@PathVariable String projectName){
        return revenueService.getByProjectName(projectName);
    }

    @PutMapping("/{projectName}")
    public ResponseEntity<?> updateRevenue(@PathVariable String projectName,@RequestBody RevenueUpdateRequest revenueUpdateRequest){
        return revenueService.updateRevenueByProjectName(projectName,revenueUpdateRequest);
    }

    @DeleteMapping("/{projectName}")
    public ResponseEntity<?> deleteRevenue(@PathVariable String projectName){
        return revenueService.deleteRevenueByProjectName(projectName);
    }

}
