package com.example.efilingbazaar.service;

import java.io.File;
import java.io.IOException;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.exception.MainServiceException;
import com.example.efilingbazaar.mapper.MainServiceMapper;
import com.example.efilingbazaar.repository.MainServiceRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.exception.SubServiceException;
import com.example.efilingbazaar.mapper.SubServiceMapper;
import com.example.efilingbazaar.repository.SubServiceRepository;
import com.example.efilingbazaar.request.SubServiceRequest;
import com.example.efilingbazaar.request.SubServiceRequest2;

@Service
@Slf4j
@Data
public class SubService {
	
	 private SubServiceRepository subServiceRepository;
	 private SubServiceMapper subServiceMapper;
	 private String PATH;



	 @Autowired
	    public SubService(SubServiceRepository subServiceRepository,
						  SubServiceMapper subServiceMapper,
		                  @Value("${file.upload.path}")String filePaths
		) {
			this.subServiceRepository = subServiceRepository;
			this.subServiceMapper= subServiceMapper;

			try {
				// Create a File object using the provided path
			/*Resource resource = new ClassPathResource(filePath);
			this.PATH = resource.getFile().getAbsolutePath();
			this.PATH=filePath;*/
				File newFile = new File(filePaths+"SubServiceImages/");
				if (!newFile.exists()){
					newFile.mkdirs();
				}
				System.out.println("the new file name is : " +newFile.exists());

				this.PATH=filePaths+"SubServiceImages/";
			/*Path path = Files.createTempFile("testFile", ".txt");
			boolean exists = Files.exists(path);*/
				//this.PATH=PATH;
			} catch (Exception e) {
				// Handle the exception if needed
				e.printStackTrace();
			}
		}

	
	   public SubServiceEntity createSubService(SubServiceRequest request, List<MultipartFile> files) throws IOException {
			 
           SubServiceEntity entity  = subServiceMapper.toRequestEntity(request);

		   String highestSubServiceId = subServiceRepository.findHighestSubServiceId();
	        int numericPart = 1;

	        if (highestSubServiceId != null) {
	            numericPart = Integer.parseInt(highestSubServiceId.substring(2)) + 1;
	        }

	         String idFormat = "SS%03d";
	         String subServiceId = String.format(idFormat, numericPart);
			 entity.setSubServiceId(subServiceId);
			 System.out.println(subServiceId);

			    /*entity.setCreatedBy(request.getCreatedBy());
				entity.setDescription(request.getDescription());
				entity.setGovernmentSection(request.getGovernmentSection());
				entity.setStatus(request.isStatus());
				entity.setProsAndCons(request.getProsAndCons());
				entity.setLiable(request.getLiable());
				entity.setImageData(request.getImageData());
				entity.setSubServiceName(request.getSubServiceName());
				entity.setSubServiceShortName(request.getSubServiceShortName());

		        entity.setMainServiceEntity(request.getMainServiceEntity());*/

		   if (!files.isEmpty()) {
			   List<String> fileNames = new ArrayList<>();
			   for (MultipartFile file : files) {
				   String fileName = PATH + file.getOriginalFilename();
				   byte[] document = file.getBytes();
				   String fileType = file.getContentType();
				   file.transferTo(new File(fileName));
				   fileNames.add(fileName);

				   // Process document data as needed...
			   }
			   entity.setSubServiceFilePaths(fileNames);
		   }
		        entity.setCreatedDate(LocalDate.now());
	        
	        return subServiceRepository.save(entity);
	    }


	   public SubServiceRequest2 getSubServiceById(String subServiceId) {
	        Optional<SubServiceEntity> subServiceEntityOptional = subServiceRepository.findBySubServiceId(subServiceId);

	        if (subServiceEntityOptional.isPresent()) {
	            SubServiceEntity subServiceEntity = subServiceEntityOptional.get();
				log.info("The retrieved subservice id is : {} ",subServiceId);
				log.info("the retrieved subservice with mainservice is  : {}", subServiceEntity);
	            SubServiceRequest2 subServiceRequest = subServiceMapper.entityToRequest(subServiceEntity);
	            return subServiceRequest;
	        } else {
	            return null; // Return null instead of creating an empty MainServiceResponse
	        }
	    }
		 

		 public List<SubServiceRequest2> getAllSubServicesWithMainServices() {
			 List<SubServiceEntity> subServices = subServiceRepository.findAll();
			 log.info("the size of the subservice is : {} ", subServices.size());
			 log.info("the retrieved subservice with mainservice are : {}", subServices);
			 List<SubServiceRequest2> subServiceRequest = subServices.stream()
			         .map(subServiceMapper::entityToRequest)
			         .collect(Collectors.toList());
			return subServiceRequest;
		 }
	   /* private SubServiceResponse convertToSubResponseDTO(SubServiceEntity entity) {
	    	
	        SubServiceResponse subdto = new SubServiceResponse();
	        subdto.setSubServiceId(entity.getSubServiceId());
	        subdto.setSubServiceName(entity.getSubServiceName());
	        subdto.setSubServiceShortName(entity.getSubServiceShortName());
	        subdto.setDescription(entity.getDescription());
	        subdto.setGovernmentSection(entity.getGovernmentSection());
	        subdto.setLiable(entity.getLiable());
	        subdto.setProsAndCons(entity.getProsAndCons());
	        subdto.setStatus(entity.isStatus());
	        subdto.setCreatedBy(entity.getCreatedBy());
	        subdto.setCreatedDate(entity.getCreatedDate());
	        subdto.setUpdatedDate(entity.getUpdatedDate());

	        // Set the main service for the sub-service
	        subdto.setMainServiceEntity(mainService.convertToResponseMainSubDTO(entity.getMainServiceEntity()));


	        return subdto;
	    }*/
	        
	    public SubServiceRequest updatedSubService(String subServiceId, SubServiceRequest subrequest, List<MultipartFile> files) throws IOException {
	        SubServiceEntity updatedSubservice = subServiceRepository.findById(subServiceId).orElse(null);
	        
	        if (updatedSubservice == null) {
				log.info("The subservice with the given id is not found {} : ",subrequest.getSubServiceId());
				throw new SubServiceException("Sub Service with ID " + subServiceId + " not found.");
	        }
		    //subServiceMapper.updateEntityFromRequest(subrequest, updatedSubservice);

	        updatedSubservice.setSubServiceName(subrequest.getSubServiceName());
	        updatedSubservice.setSubServiceShortName(subrequest.getSubServiceShortName());
	        updatedSubservice.setDescription(subrequest.getDescription());
	        updatedSubservice.setStatus(subrequest.isStatus());
	        updatedSubservice.setUpdatedDate(LocalDate.now());

			if (!files.isEmpty()) {
				List<String> fileNames = new ArrayList<>();
				for (MultipartFile file : files) {
					String fileName = PATH + file.getOriginalFilename();
					byte[] document = file.getBytes();
					String fileType = file.getContentType();
					file.transferTo(new File(fileName));
					fileNames.add(fileName);
					// Process document data as needed...
				}
				updatedSubservice.setSubServiceFilePaths(fileNames);
			}
			subServiceRepository.save(updatedSubservice); // Use the same entity to save changes
			return subrequest;
		}


	public SubServiceEntity deleteSubServiceById(String subServiceId) {
		Optional<SubServiceEntity> subServiceOptional = subServiceRepository.findBySubServiceId(subServiceId);
		if (!subServiceOptional.isPresent()) {
			log.info("The subservice with the given id is not found {} : ", subServiceId);
			throw new SubServiceException("Sub Service with ID " + subServiceId + " not found.");
		}

		SubServiceEntity subService = subServiceOptional.get();
		subServiceRepository.delete(subService);
		return subService;
	}
	    
	    public boolean existsById(String subServiceId) {
	        return subServiceRepository.existsById(subServiceId);
	    }

	private String previousCount = "";
	private LocalDate lastUpdatedDate = LocalDate.now();

	public int countSubservices() {
		try {
			return (int) subServiceRepository.count(); // Count all employees in the database
		} catch (Exception e) {
			log.error("Failed to count sub services: {}", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Failed to count sub services");
		}
	}

}
