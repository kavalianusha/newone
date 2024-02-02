package com.pathbreaker.payslip.service;

import com.pathbreaker.payslip.entity.PaySlip;
import com.pathbreaker.payslip.request.PaySlipUpdateRequest;
import com.pathbreaker.payslip.request.PaySlipsRequest;
import com.pathbreaker.payslip.response.PaySlipsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PaySlipService {
    ResponseEntity<?> uploadFile(PaySlipsRequest paySlipsRequest,MultipartFile file);

    ResponseEntity<?> downlaodPayslipById(String employeeId,Long id);

    ResponseEntity<?> updatePayslipById(String employeeId, Long id, PaySlipUpdateRequest paySlipsRequest, MultipartFile file);

    ResponseEntity<?> deletePayslipById(String employeeId,Long id);

    ResponseEntity<byte[]> getPayslipById(String employeeId,Long id);

    List<PaySlipsResponse> getAllPayslip();

    List<PaySlipsResponse> getByEmployeeId(String employeeId);
}
