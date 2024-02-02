package com.example.efilingbazaar.response;

import java.time.LocalDate;
import java.util.List;

import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.request.MainServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubServiceResponse {
	
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

}
