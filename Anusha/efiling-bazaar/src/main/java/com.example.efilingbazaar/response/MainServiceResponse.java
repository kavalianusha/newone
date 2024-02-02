package com.example.efilingbazaar.response;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import com.example.efilingbazaar.entity.MainServiceEntity;
import lombok.Data;

@Data
public class MainServiceResponse {
	
	   private String mainServiceId;
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
	    private List<SubServiceResponse> subServices;


}
