package com.pathbreaker.payslip.controller;

import com.pathbreaker.payslip.entity.PaySlip;
import com.pathbreaker.payslip.request.PaySlipUpdateRequest;
import com.pathbreaker.payslip.request.PaySlipsRequest;
import com.pathbreaker.payslip.response.PaySlipsResponse;
import com.pathbreaker.payslip.service.PaySlipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/payslip")
public class PaySlipController {

    @Autowired
    private PaySlipService paySlipService;

    @RequestMapping(value = "/upload-document", method= RequestMethod.POST,
                   consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDocument(@ModelAttribute PaySlipsRequest paySlipsRequest,
                                            @RequestPart("file") MultipartFile file){
        System.out.println("the contrller upload hitted..");
        return paySlipService.uploadFile(paySlipsRequest,file);
    }

    @GetMapping("/image/{employeeId}/{id}")
    public ResponseEntity<byte[]> getPayslipById(@PathVariable String employeeId,
                                                 @PathVariable Long id){
        return paySlipService.getPayslipById(employeeId,id);
    }

    @GetMapping("/download/{employeeId}/{id}")
    public ResponseEntity<?> downlaodPayslipById(@PathVariable String employeeId,
                                                 @PathVariable Long id){
        return paySlipService.downlaodPayslipById(employeeId,id);
    }

    @GetMapping("/all")
    public List<PaySlipsResponse> getAllPayslip(){
        return paySlipService.getAllPayslip();
    }

    @GetMapping("/{employeeId}")
    public List<PaySlipsResponse> getByEmployeeId(@PathVariable String employeeId){
        return paySlipService.getByEmployeeId(employeeId);
    }


    @RequestMapping(value = "/{employeeId}/{id}", method= RequestMethod.PUT, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePayslipById(@PathVariable String employeeId,
                                               @PathVariable Long id,
                                               @ModelAttribute PaySlipUpdateRequest paySlipsRequest,
                                               @RequestPart("file") MultipartFile file){
        return paySlipService.updatePayslipById(employeeId,id,paySlipsRequest,file);

    }

    @DeleteMapping("/{employeeId}/{id}")
    public ResponseEntity<?> deletePayslipById(@PathVariable String employeeId,
                                               @PathVariable Long id){
        return paySlipService.deletePayslipById(employeeId,id);
    }

}
