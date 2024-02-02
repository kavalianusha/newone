package com.example.efilingbazaar.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class MainServiceRequest {
	
	    private String mainServiceName;
	    private String mainServiceShortName;
	    private String governmentSection;
	    private String liable;
	    private String prosAndCons;
	    private String description;
	    private boolean status;
		private List<String> mainServiceFilePaths;
		private String fileType;
     	private String createdBy;
	    private LocalDate createdDate;
	    private LocalDate updatedDate;
	    private String mainServiceId;
	  //  private List<SubServiceRequest> subServices;

}

