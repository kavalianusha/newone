package com.example.efilingbazaar.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SubServiceRequest2 {
	
	    private String subServiceId;
	    private String subServiceName;
	    private String subServiceShortName;
	    private String governmentSection;
	    private String liable;
	    private String prosAndCons;
	    private String description;
	    private boolean status;
	    private List<String> subServiceFilePaths;
	    private String fileType;
	    private String createdBy;
	    private LocalDate createdDate;
	    private LocalDate updatedDate;
	    private MainServiceRequest mainServiceEntity;

}
