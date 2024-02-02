package com.example.efilingbazaar.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.response.ResultCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.exception.SubServiceException;
import com.example.efilingbazaar.repository.SubServiceRepository;
import com.example.efilingbazaar.request.SubServiceRequest;
import com.example.efilingbazaar.request.SubServiceRequest2;
import com.example.efilingbazaar.response.ResultResponse;
import com.example.efilingbazaar.service.SubService;

@Controller
@CrossOrigin(origins="*")
@Slf4j
public class SubServiceController {
	
	   
	@Autowired
    private SubServiceRepository subServiceRepository;
    
    @Autowired
    private SubService subService;
    
    public String findHighestSubServiceId() {
        String highestSubServiceId = subServiceRepository.findHighestSubServiceId();
        if (highestSubServiceId == null) {
            highestSubServiceId = "SS000";
        }
        return highestSubServiceId;
 }
	     
	   @RequestMapping(value = "/post/subservice",
				   method=RequestMethod.POST,
				   consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<Object> createSubService(@ModelAttribute SubServiceRequest request,
													   @RequestParam("files") List<MultipartFile> files) throws IOException {
	        try {
	        	SubServiceEntity createdService = subService.createSubService(request, files);
	        	ResultResponse result = new ResultResponse();
	            result.setResult("Sub Service created successfully: " + createdService.getSubServiceId());
				log.info("Sub Service created successfully with Id {} : ",createdService.getSubServiceId());

				return ResponseEntity.ok(result);
	        } catch (Exception ex) {
	        	ResultResponse result = new ResultResponse();
				result.setResult("An error occurred while creating Sub Service: " + ex.getMessage());
				log.info("An error occurred while creating Sub Service: {} : ", ex.getMessage());
				return ResponseEntity.ok(result);
	        }
	    }

	   @GetMapping("/get/subservice")
	   public ResponseEntity<List<SubServiceRequest2>> getAllSubServicesWithMainServices() {
	        try {
	            List<SubServiceRequest2> subServices = subService.getAllSubServicesWithMainServices();
	            return ResponseEntity.ok(subServices);
	        } catch (Exception e) {
				log.error("error getting all details of subservice : {} ", e.getMessage());
				e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	   
	   @GetMapping("/get/subservice/{subServiceId}")
	   public ResponseEntity<SubServiceRequest2> getSubServiceById(@PathVariable String subServiceId) {
	        try {
	        	SubServiceRequest2 subServiceRequest = subService.getSubServiceById(subServiceId);
	            if (subServiceRequest == null) {
	                return ResponseEntity.notFound().build();
	            }
	            return ResponseEntity.ok(subServiceRequest);
	        } catch (Exception e) {
				log.error("error getting one details of subservice: {} ", e.getMessage());
				e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	}
	   
	   @PutMapping(value = "/update/subservice/{subServiceId}", produces = "application/json")
	    public ResponseEntity<?> updateSubService(
	            @PathVariable String subServiceId,
	            @ModelAttribute SubServiceRequest subrequest,
	            @RequestParam("files") List<MultipartFile> files) {

	        try {
	            // Check if the sub service with the given ID exists in the database
	            if (!subService.existsById(subServiceId)) {
	            	ResultResponse result = new ResultResponse();
		            result.setResult("Sub Service with ID " + subServiceId + " not found.");
					log.info("The subservice with the given id is not found {} : ",subrequest.getSubServiceId());
					return ResponseEntity.ok(result);
	            }

	            // Perform the update operation using the service
	            subService.updatedSubService(subServiceId, subrequest, files);
	            ResultResponse result = new ResultResponse();
				result.setResult("Sub Service with ID " + subServiceId + " has been updated.");
				log.info("The subservice with the given id is updated : {}  ",subrequest.getSubServiceId());
				return ResponseEntity.ok(result);
	            
	        } catch (Exception ex) {
	            // Handle any other exceptions 
	        	ResultResponse result = new ResultResponse();
	            result.setResult("An error occurred while updating the sub service");
				log.info("An error occurred while updating Sub Service: {} : ", ex.getMessage());
				System.out.println("result is "+ex);
	            return ResponseEntity.ok(result);
	        }
	    }

	   @DeleteMapping("/delete/subservice/{subServiceId}")
	    public ResponseEntity<?> deleteSubServiceById(@PathVariable String subServiceId) {
	        try {
	            subService.deleteSubServiceById(subServiceId);
	        	ResultResponse result = new ResultResponse();
	            result.setResult("Sub Service with ID: " + subServiceId + " has been deleted.");
				log.info("The subservice with the given id is deleted {} : ",subServiceId);

				return ResponseEntity.ok(result);
	        } catch (SubServiceException ex) {
	        	ResultResponse result = new ResultResponse();
	            result.setResult("Sub Service with ID: " + subServiceId + " not found");
				log.info("The subservice with the given id not found: ",subServiceId);
				return ResponseEntity.ok(result);	        }
			catch (Exception ex) {
	        	ResultResponse result = new ResultResponse();
	            result.setResult("An error occurred while deleting the sub service");
				log.info("An error occurred while deleting the sub service ",subServiceId);
				return ResponseEntity.ok(result);
	        }
	   }
	@GetMapping("/subservice/count")
	public ResponseEntity<ResultCount> countSubservice() {
		String currentCount = String.valueOf(subService.countSubservices());
		String count = currentCount;
		LocalDate lastUpdatedDate = subService.getLastUpdatedDate();

		if (!count.equals(subService.getPreviousCount())) {
			lastUpdatedDate = LocalDate.now();
			subService.setPreviousCount(count);
			subService.setLastUpdatedDate(lastUpdatedDate);
		}
		String message = count;
		ResultCount response = new ResultCount();
		response.setResult(message);
		response.setDate(lastUpdatedDate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public SubServiceRepository getSubServiceRepository() {
		return subServiceRepository;
	}

	public void setSubServiceRepository(SubServiceRepository subServiceRepository) {
		this.subServiceRepository = subServiceRepository;
	}

	@GetMapping("/subservice/{subServiceId}/images/{imageName}")
	public ResponseEntity<?> getSubserviceImage(@PathVariable String subServiceId, @PathVariable String imageName) {
		Optional<SubServiceEntity> subServiceEntityOptional = subServiceRepository.findById(subServiceId);

		if (subServiceEntityOptional.isPresent()) {
			List<String> filePaths = subServiceEntityOptional.get().getSubServiceFilePaths();
			for (String filePath : filePaths) {
				if (Paths.get(filePath).getFileName().toString().equals(imageName)) {
					try {
						Path documentPath = Paths.get(filePath);
						byte[] documentBytes = Files.readAllBytes(documentPath);

						HttpHeaders headers = new HttpHeaders();

						// Determine content type based on file extension
						String contentType = Files.probeContentType(documentPath);
						if (contentType != null) {
							log.info("image shown successfully..........");
							headers.setContentType(MediaType.parseMediaType(contentType));
						}

						return new ResponseEntity<>(documentBytes, headers, HttpStatus.OK);
					} catch (IOException e) {
						e.printStackTrace();
						log.error("error while showing the image i.e due to the image size is maximum or more than expected");
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}
				}
			}
			// If we reach here, the image was not found
			ResultResponse result = new ResultResponse();
			log.error("Image/document not found for subservice with ID: " + subServiceId);
			result.setResult("Image/document not found for subservice with ID: " + subServiceId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(result);
		}
		// If we reach here, the customer was not found
		ResultResponse result = new ResultResponse();
		log.error("SubService with ID"  + subServiceId +" not found");
		result.setResult("SubService with ID " + subServiceId + " not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(result);
	}
}
