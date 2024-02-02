package com.example.efilingbazaar.controller;

import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.example.efilingbazaar.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.exception.MainServiceException;
import com.example.efilingbazaar.repository.MainServiceRepository;
import com.example.efilingbazaar.request.MainServiceRequest;
import com.example.efilingbazaar.response.MainServiceResponse;
import com.example.efilingbazaar.response.ResultResponse;
import com.example.efilingbazaar.service.MainService;

@RestController
@CrossOrigin(origins="*")
@Slf4j
public class MainServiceController {
	
	@Autowired
    private MainServiceRepository mainServiceRepository;
    
    @Autowired
    private MainService mainService;
    
    public String findHighestMainServiceId() {
		String highestMainServiceId = mainServiceRepository.findHighestMainServiceId();
		if (highestMainServiceId == null) {
			highestMainServiceId = "MS000";
		}
		return highestMainServiceId;
	}
	@RequestMapping(value = "/post/mainservice", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createMainService(@ModelAttribute MainServiceRequest request,
											   @RequestParam("files") List<MultipartFile> files) throws IOException {
		try {
			if (files.isEmpty()) {
				ResultResponse result = new ResultResponse();
				result.setResult("File cannot be empty.");
				log.info("File cannot be empty.");
				return ResponseEntity.badRequest().body(result);
			}
			MainServiceEntity createdService = mainService.createMainService(request,files);

			ResultResponse result = new ResultResponse();
			result.setResult("Main Service created successfully with Id: " + createdService.getMainServiceId());
			log.info("Main Service created successfully with Id: {}", createdService.getMainServiceId());
			return ResponseEntity.ok(result);
		} catch (InvalidFileNameException ex) {
			ResultResponse result = new ResultResponse();
			result.setResult("Invalid file uploaded: " + ex.getMessage());
			log.error("Invalid file uploaded: {}", ex.getMessage());
			return ResponseEntity.badRequest().body(result);
		} catch (Exception ex) {
			ResultResponse result = new ResultResponse();
			result.setResult("An error occurred while creating Main Service: " + ex.getMessage());
			log.error("An error occurred while creating Main Service.", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(result);
		}
	}
	 @GetMapping("/get/mainservice")
	 public ResponseEntity<List<MainServiceResponse>> getAllMainServicesWithSubServices() {
	        try {
	            List<MainServiceResponse> mainServices = mainService.getAllMainServicesWithSubServices();
	            return ResponseEntity.ok(mainServices);
	        } catch (Exception e) {
				log.error("error getting all details of mainservice: {} ", e.getMessage());
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	 
	 @GetMapping("/get/mainservice/{mainServiceId}")
	 public ResponseEntity<MainServiceResponse> getMainServiceById(@PathVariable String mainServiceId) {
	        try {
	        	MainServiceResponse mainServiceResponse = mainService.getMainServiceById(mainServiceId);
	            if (mainServiceResponse == null) {
	                return ResponseEntity.notFound().build();
	            }
	            return ResponseEntity.ok(mainServiceResponse);
	        } catch (Exception e) {
	            e.printStackTrace();
				log.error("error getting one details of mainservice: {} ", e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

	
	 @PutMapping(value = "/update/mainservice/{mainServiceId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity<ResultResponse> updateMainService(
	         @PathVariable String mainServiceId,
	         @ModelAttribute MainServiceRequest mrequest,
	         @RequestParam("files") List<MultipartFile> files) {

	     try {
	         // Check if the main service with the given ID exists in the database
	         if (!mainService.existsById(mainServiceId)) {
	             ResultResponse result = new ResultResponse();
	             result.setResult("Main Service with ID " + mainServiceId + " not found.");
				 log.info("The mainservice with the given id is not found {} : ",mrequest.getMainServiceId());
				 return ResponseEntity.ok(result);
	         }

	         // Perform the update operation using the service
	         mainService.updatedMainService(mainServiceId, mrequest, files);
	         ResultResponse result = new ResultResponse();
	         result.setResult("Main Service with ID " + mainServiceId + " has been updated.");
			 log.info("The mainservice with the given id is updated : {}  ",mrequest.getMainServiceId());
			 return ResponseEntity.ok(result);

	     } catch (Exception ex) {
	         // Log the exception
	         ex.printStackTrace();
	         
	         ResultResponse result = new ResultResponse();
	         result.setResult("An error occurred while updating the main service: " + ex.getMessage());
			 log.info("An error occurred while updating the main service : {} ",ex.getMessage());
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	     }
	 }


    @DeleteMapping("/delete/mainservice/{mainServiceId}")
    public ResponseEntity<Object> deleteMainService(@PathVariable String mainServiceId) {
        try {
            mainService.deleteMainService(mainServiceId);
            ResultResponse result = new ResultResponse();
            result.setResult("Main Service with ID: " + mainServiceId + " and its associated SubServices have been deleted.");
			log.info("The mainservice with the given id and its associates deleted {} : ",mainServiceId);

			return ResponseEntity.ok(result);
        } catch (MainServiceException ex) {
        	 ResultResponse result = new ResultResponse();
             result.setResult("Main Service with ID " + mainServiceId + " not found.");
			log.info("The mainservice with the given id not found: ",mainServiceId);
			return ResponseEntity.ok(result);
        } catch (Exception ex) {
        	 ResultResponse result = new ResultResponse();
             result.setResult("An error occurred while deleting the main service");
			log.info("An error occurred while deleting the main service ",mainServiceId);

			return ResponseEntity.ok(result);
        }
    }

	public MainServiceRepository getMainServiceRepository() {
		return mainServiceRepository;
	}

	public void setMainServiceRepository(MainServiceRepository mainServiceRepository) {
		this.mainServiceRepository = mainServiceRepository;
	}

	@GetMapping("/mainservice/{mainServiceId}/images/{imageName}")
	public ResponseEntity<?> getMainserviceImage(@PathVariable String mainServiceId, @PathVariable String imageName) {
		Optional<MainServiceEntity> mainServiceEntityOptional = mainServiceRepository.findById(mainServiceId);

		if (mainServiceEntityOptional.isPresent()) {
			List<String> filePaths = mainServiceEntityOptional.get().getMainServiceFilePaths();
			for (String filePath : filePaths) {
				if (Paths.get(filePath).getFileName().toString().equals(imageName)) {
					try {
						Path documentPath = Paths.get(filePath);
						byte[] documentBytes = Files.readAllBytes(documentPath);

						HttpHeaders headers = new HttpHeaders();

						// Determine content type based on file extension
						String contentType = Files.probeContentType(documentPath);
						if (contentType != null) {
							headers.setContentType(MediaType.parseMediaType(contentType));
						}

						return new ResponseEntity<>(documentBytes, headers, HttpStatus.OK);
					} catch (IOException e) {
						e.printStackTrace();
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}
				}
			}

			ResultResponse result = new ResultResponse();
			result.setResult("Image/document not found for Mainservice with ID: " + mainServiceId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(result);
		}
		// If we reach here, the customer was not found
		ResultResponse result = new ResultResponse();
		result.setResult("MainService with ID " + mainServiceId + " not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(result);
	}

	
}
