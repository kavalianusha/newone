package com.example.efilingbazaar.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.efilingbazaar.entity.CustomerEntity;
import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.request.CustomerRequest;
import com.example.efilingbazaar.request.SubServiceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.exception.MainServiceException;
import com.example.efilingbazaar.mapper.MainServiceMapper;
import com.example.efilingbazaar.repository.MainServiceRepository;
import com.example.efilingbazaar.request.MainServiceRequest;
import com.example.efilingbazaar.response.MainServiceResponse;
@Service
@Slf4j
public class MainService {

	private MainServiceMapper mainServiceMapper;

	private MainServiceRepository mainServiceRepository;

	//private MultipartFile files;

	//private final String PATH = "D:\\Efiling_WS\\updated-efiling-bazaar\\anushamamatha2\\efiling-bazaar\\src\\test\\resources\\MainServiceImages\\";
	private String PATH;

	/*@Autowired
	public MainService(MultipartFile file) throws IOException {
		this.file = file;
		Resource resource = new ClassPathResource("test/resources/MainServiceImages/");
		PATH = resource.getFile().getAbsolutePath();
	}*/

	@Autowired
	public MainService(
			MainServiceRepository mainServiceRepository,
			MainServiceMapper mainServiceMapper,
			@Value("${file.upload.path}") String mainServiceFilePaths
	) {
		this.mainServiceRepository = mainServiceRepository;
		this.mainServiceMapper = mainServiceMapper;

		try {
			// Create a File object using the provided path
			/*Resource resource = new ClassPathResource(filePath);
			this.PATH = resource.getFile().getAbsolutePath();
			this.PATH=filePath;*/
			File newFile = new File(mainServiceFilePaths+"MainServiceImages/");
			if (!newFile.exists()){
				newFile.mkdirs();
			}
			System.out.println("the new file name is : " +newFile.exists());

			this.PATH=mainServiceFilePaths+"MainServiceImages/";
			/*Path path = Files.createTempFile("testFile", ".txt");
			boolean exists = Files.exists(path);*/
			//this.PATH=PATH;
		} catch (Exception e) {
			// Handle the exception if needed
			e.printStackTrace();
		}
	}
	public MainServiceEntity createMainService(MainServiceRequest request, List<MultipartFile> files) throws IOException {
		if (mainServiceRepository.existsByMainServiceName(request.getMainServiceName())) {
			throw new MainServiceException("Main Service with the given name already exists.");
		}
		MainServiceEntity entity = mainServiceMapper.toMainServiceEntity(request);

		String highestMainServiceId = mainServiceRepository.findHighestMainServiceId();
		int numericPart = 1;

		if (highestMainServiceId != null) {
			numericPart = Integer.parseInt(highestMainServiceId.substring(2)) + 1;
		}
		String idFormat = "MS%03d"; // The %03d means a 3-digit zero-padded number
		String mainServiceId = String.format(idFormat, numericPart);
		entity.setMainServiceId(mainServiceId);

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
			entity.setMainServiceFilePaths(fileNames);
		}
			entity.setCreatedDate(LocalDate.now());
			// Save the entity to the database
			return mainServiceRepository.save(entity);
	}

	public MainServiceResponse getMainServiceById(String mainServiceId) {
		Optional<MainServiceEntity> mainServiceEntityOptional = mainServiceRepository.findByMainServiceId(mainServiceId);
		if (mainServiceEntityOptional.isPresent()) {
			MainServiceEntity mainServiceEntity = mainServiceEntityOptional.get();
			log.info("The retrieved mainservice id is {} : ",mainServiceId);
			log.info("the retrieved mainservice with subservice is {}  : ", mainServiceEntity);
			return mainServiceMapper.toResponse(mainServiceEntity);
        } else {
            return null; // Return null instead of creating an empty MainServiceResponse
        }
    }
	public List<MainServiceResponse> getAllMainServicesWithSubServices() {
		List<MainServiceEntity> mainServices = mainServiceRepository.findAll();
		log.info("the size of the mainservice is {} : ", mainServices.size());
		//log.info("the retrieved mainservice with subservice are {} : ", mainServices);
		List<MainServiceResponse> mainServiceResponses = mainServices.stream()
				.map(mainServiceMapper::toResponse)
				.collect(Collectors.toList());
		return mainServiceResponses;
	}
	public MainServiceEntity updatedMainService(String mainServiceId, MainServiceRequest mrequest, List<MultipartFile> files)
			throws IOException {
		MainServiceEntity updatedMainService = mainServiceRepository.findByMainServiceId(mainServiceId).orElse(null);
		if (updatedMainService == null) {
			log.info("The mainservice with the given id is not found {} : ", mrequest.getMainServiceId());
			throw new MainServiceException("Main Service with ID " + mainServiceId + " not found.");
		}
		// Update the fields of the main service object
		//mainServiceMapper.updateEntityFromRequest(mrequest, updatedMainService);
		updatedMainService.setMainServiceName(mrequest.getMainServiceName());
		updatedMainService.setMainServiceShortName(mrequest.getMainServiceShortName());
		updatedMainService.setDescription(mrequest.getDescription());
		updatedMainService.setStatus(mrequest.isStatus());
		updatedMainService.setUpdatedDate(LocalDate.now());
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
			updatedMainService.setMainServiceFilePaths(fileNames);
		}
		updatedMainService.setUpdatedDate(LocalDate.now());
			mainServiceRepository.save(updatedMainService);
			return updatedMainService;
	}
	public MainServiceEntity deleteMainService(String mainServiceId) {
		Optional<MainServiceEntity> mainServiceOptional = mainServiceRepository.findByMainServiceId(mainServiceId);
		if (!mainServiceOptional.isPresent()) {
			log.info("The mainservice with the given id is not found {} : ",mainServiceId);
			throw new MainServiceException("Main Service with ID " + mainServiceId + " not found.");
		}

		MainServiceEntity mainService = mainServiceOptional.get();
		mainServiceRepository.delete(mainService);
		return mainService;
	}
	public boolean existsById(String mainServiceId) {
		return mainServiceRepository.existsById(mainServiceId);
	}

}
