package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.entity.Employee;
import com.pathbreaker.payslip.entity.PaySlip;
import com.pathbreaker.payslip.exception.Exceptions;
import com.pathbreaker.payslip.exception.NotFoundException;
import com.pathbreaker.payslip.mappers.PaySlipMapper;
import com.pathbreaker.payslip.repository.EmployeeRepository;
import com.pathbreaker.payslip.repository.PaySlipRepository;
import com.pathbreaker.payslip.request.PaySlipUpdateRequest;
import com.pathbreaker.payslip.request.PaySlipsRequest;
import com.pathbreaker.payslip.response.PaySlipsResponse;
import com.pathbreaker.payslip.response.ResultResponse;
import com.pathbreaker.payslip.service.PaySlipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaySlipServiceImpl implements PaySlipService {

    @Autowired
    public PaySlipServiceImpl(PaySlipRepository paySlipRepository,
                              PaySlipMapper paySlipMapper,
                              EmployeeRepository employeeRepository,
                              @Value("${file.upload.path}") String filePath) {
        this.paySlipRepository = paySlipRepository;
        this.paySlipMapper = paySlipMapper;
        this.PATH = filePath;
        this.employeeRepository = employeeRepository;
    }
    private final PaySlipMapper paySlipMapper;
    private final PaySlipRepository paySlipRepository;
    private final EmployeeRepository employeeRepository;
    private String PATH;

    @Override
    public ResponseEntity<?> uploadFile(PaySlipsRequest paySlipsRequest, MultipartFile file){
        try {
            System.out.println("hited service..");
            PaySlip paySlipUploadEntity = paySlipMapper.entityToRequest(paySlipsRequest);

            if (file.isEmpty()) {
                ResultResponse result = new ResultResponse();
                result.setResult("File cannot be empty.");
                log.info("File cannot be empty.");
                throw new NotFoundException(HttpStatus.BAD_REQUEST, "The File is empty");
            }  else {
                byte[] document = file.getBytes();
                System.out.println(document);

                String fileType = file.getContentType();
                System.out.println("The original name : " +file.getOriginalFilename());

                // Add a unique identifier to the file name
                String employeeId = paySlipUploadEntity.getEmployeeId();
                System.out.println(employeeId);
                String fileName = employeeId + "_" + file.getOriginalFilename();
                String filePath = PATH + fileName;

                file.transferTo(new File(filePath));
                paySlipUploadEntity.setFilePaths(filePath);

                paySlipRepository.save(paySlipUploadEntity);

                ResultResponse result = new ResultResponse();
                log.info("Pay slip uploaded successfully");
                result.setResult("Pay slip uploaded successfully");

                return ResponseEntity.ok(result);
            }
        } catch (Exception ex) {
            log.error("An error occurred while uploading the document", ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while uploading the document");
        }
    }

    @Override
    public List<PaySlipsResponse> getAllPayslip() {
        List<PaySlipsResponse> paySlipsResponses = new ArrayList<>();
        List<PaySlip> paySlipUploadEntities = paySlipRepository.findAll();

        for (PaySlip paySlip : paySlipUploadEntities) {
            String employeeId = paySlip.getEmployeeId();
            // Perform a separate query to get Employee details using employeeId
            Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(employeeId);
            // Use orElse to provide a default Employee if not found
            Employee employee = employeeOptional.orElse(new Employee());

            PaySlipsResponse paySlipsResponse = new PaySlipsResponse();
            paySlipsResponse.setEmployeeId(employeeId);
            paySlipsResponse.setFirstName(employee.getFirstName());
            paySlipsResponse.setLastName(employee.getLastName());
            paySlipsResponse.setId(paySlip.getId());
            paySlipsResponse.setMonth(paySlip.getMonth());
            paySlipsResponse.setFinancialYear(paySlip.getFinancialYear());
            paySlipsResponse.setFilePaths(paySlip.getFilePaths());

            paySlipsResponses.add(paySlipsResponse);
        }

        return paySlipsResponses;
    }

    @Override
    public List<PaySlipsResponse> getByEmployeeId(String employeeId) {
        try {
            List<PaySlip> paySlipUploadEntities = paySlipRepository.findByEmployeeId(employeeId);

            if (!paySlipUploadEntities.isEmpty()) {
                List<PaySlipsResponse> paySlipResponses = new ArrayList<>();

                // Perform a separate query to get Employee details using employeeId
                Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(employeeId);
                // Use orElse to provide a default Employee if not found
                Employee employee = employeeOptional.orElse(new Employee());

                for (PaySlip paySlip : paySlipUploadEntities) {
                    PaySlipsResponse paySlipResponse = new PaySlipsResponse();
                    paySlipResponse.setEmployeeId(employeeId);
                    paySlipResponse.setFirstName(employee.getFirstName());
                    paySlipResponse.setLastName(employee.getLastName());
                    paySlipResponse.setId(paySlip.getId());
                    paySlipResponse.setMonth(paySlip.getMonth());
                    paySlipResponse.setFinancialYear(paySlip.getFinancialYear());
                    paySlipResponse.setFilePaths(paySlip.getFilePaths());

                    paySlipResponses.add(paySlipResponse);
                }

                return paySlipResponses;
            }
                else {
                log.error("No Payslips found for employee ID: " + employeeId);
                throw new Exceptions(HttpStatus.NOT_FOUND, "No Payslips found for employee ID: " + employeeId);
            }
        }  catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while retrieving Payslip by ID: " + employeeId, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while retrieving Payslip by ID: " + employeeId);
        }
    }

    @Override
    public ResponseEntity<byte[]> getPayslipById(String employeeId,Long id) {

        try {
            Optional<PaySlip> paySlipUploadEntity = paySlipRepository.findByEmployeeIdAndId(employeeId,id);

            if (paySlipUploadEntity.isPresent()) {
                PaySlip paySlipUpload = paySlipUploadEntity.get();
                String filePath = paySlipUpload.getFilePaths();

                byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

                HttpHeaders headers = new HttpHeaders();
                // Determine content type based on file extension
                String contentType = Files.probeContentType(Paths.get(filePath));
                headers.setContentType(MediaType.parseMediaType(contentType));

                // Set content disposition to "inline" to display in the browser
                headers.setContentDisposition(ContentDisposition.builder("inline").filename(StringUtils.getFilename(filePath)).build());

                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            } else {
                log.error("Pay slip  showing Document with ID " + id + " not found", id);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The showing Document with ID " + id + " not found");
            }
        } catch (IOException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            log.error("Error occurred while showing the document " + e);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while retrieving the document " + e);
        }
    }
    @Override
    public ResponseEntity<?> downlaodPayslipById(String employeeId,Long id) {
        try {
            Optional<PaySlip> paySlipUploadEntity = paySlipRepository.findByEmployeeIdAndId(employeeId,id);

            if (paySlipUploadEntity.isPresent()) {
                PaySlip entity = paySlipUploadEntity.get();
                String path = entity.getFilePaths();
                Path documentPath = Paths.get(path);
                byte[] documentBytes = Files.readAllBytes(documentPath);

                HttpHeaders headers = new HttpHeaders();
                // Determine content type based on file extension
                String contentType = Files.probeContentType(documentPath);
                headers.setContentType(MediaType.parseMediaType(contentType));

                // Set content disposition to "attachment" to trigger download
                String fileName = StringUtils.getFilename(path);
                headers.setContentDispositionFormData("attachment", fileName);

                return new ResponseEntity<>(documentBytes, headers, HttpStatus.OK);
            } else {
                log.error("Pay slip  Document with ID " + id + " not found", id);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Document with ID " + id + " not found");
            }
        } catch (IOException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            log.error("Error occurred while retrieving the document " + e);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while retrieving the document " + e);
        }
    }

    @Override
    public ResponseEntity<?> updatePayslipById(String employeeId,Long id ,PaySlipUpdateRequest paySlipsRequest, MultipartFile file) {
        try {
            System.out.println("hited service..");
            Optional<PaySlip> optionalPaySlip = paySlipRepository.findByEmployeeIdAndId(employeeId, id);

            if (optionalPaySlip.isPresent()) {
                PaySlip existingPaySlip = optionalPaySlip.get();
                System.out.println(existingPaySlip.getFilePaths());
                paySlipMapper.entityToUpdateRequest(paySlipsRequest, existingPaySlip);

                if (file != null && !file.isEmpty()) {
                    byte[] document = file.getBytes();
                    System.out.println(document);

                    String fileType = file.getContentType();
                    System.out.println("The original name : " + file.getOriginalFilename());

                    // Add a unique identifier to the file name
                    String employee = existingPaySlip.getEmployeeId();
                    String fileName = employee + "_" + file.getOriginalFilename();
                    String filePath = PATH + fileName;

                    // Delete the existing file
                    Path existingFilePath = Paths.get(existingPaySlip.getFilePaths());
                    Files.delete(existingFilePath);
                    // Save the new file
                    file.transferTo(new File(filePath));
                    existingPaySlip.setFilePaths(filePath);
                }

                // Save the updated entity
                paySlipRepository.save(existingPaySlip);

                ResultResponse result = new ResultResponse();
                log.info("Pay slip is updated successfully");
                result.setResult("Pay slip is updated successfully");

                return ResponseEntity.ok(result);
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "Pay slip with ID " + id + " not found");
            }
        } catch (Exception ex) {
            log.error("An error occurred while updating the pay slip", ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while updating the pay slip");
        }
    }

    @Override
    public ResponseEntity<?> deletePayslipById(String employeeId,Long id) {
        try {
            Optional<PaySlip> optionalPaySlip = paySlipRepository.findByEmployeeIdAndId(employeeId, id);

            if (optionalPaySlip.isPresent()) {
                PaySlip existingPaySlip = optionalPaySlip.get();

                // Delete the associated file
                Path existingFilePath = Paths.get(existingPaySlip.getFilePaths());
                Files.deleteIfExists(existingFilePath);

                // Delete the entity from the database
                paySlipRepository.delete(existingPaySlip);

                ResultResponse result = new ResultResponse();
                log.info("Pay slip with ID {} is deleted successfully", employeeId);
                result.setResult("Pay slip is deleted successfully");

                return ResponseEntity.ok(result);
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "Pay slip with ID " + id + " not found");
            }
        } catch (Exception ex) {
            log.error("An error occurred while deleting the pay slip", ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while deleting the pay slip"+ex);
        }
    }

}
