package com.pathbreaker.pmp.service;

import com.pathbreaker.pmp.entity.RevenueEntity;
import com.pathbreaker.pmp.request.RevenueRequest;
import com.pathbreaker.pmp.request.RevenueUpdateRequest;
import com.pathbreaker.pmp.response.RevenueResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RevenueService {


    ResponseEntity<?> createRevenue(RevenueRequest revenueRequest);

    List<RevenueResponse> getAllRevenues();

    RevenueResponse getByProjectName(String projectName);

    ResponseEntity<?> updateRevenueByProjectName(String projectName, RevenueUpdateRequest revenueUpdateRequest);

    ResponseEntity<?> deleteRevenueByProjectName(String projectName);
}
