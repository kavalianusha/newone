package com.pathbreaker.payslip.controller;

import com.pathbreaker.payslip.entity.Status;
import com.pathbreaker.payslip.request.StatusRequest;
import com.pathbreaker.payslip.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping("/add")
    public ResponseEntity<?> createStatus(@RequestBody StatusRequest statusRequest){
        return statusService.createStatus(statusRequest);
    }

    @GetMapping("/all")
    public List<Status> getAllStatus(){
        return statusService.getAllStatus();
    }

    @GetMapping("/{id}")
    public Optional<Status> getById(@PathVariable int id){
        return statusService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id,@RequestBody StatusRequest statusRequest){
        return statusService.updateById(id,statusRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        return statusService.deleteById(id);
    }

}
