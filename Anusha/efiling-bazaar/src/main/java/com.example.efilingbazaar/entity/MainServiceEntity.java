package com.example.efilingbazaar.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.efilingbazaar.mapper.MainServiceMapper;
import com.example.efilingbazaar.response.MainServiceResponse;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="MainserviceRegister")
public class MainServiceEntity {
	
	    @Id
	    @Column(name = "mainServiceId")
        private String mainServiceId;
	 
	    @OneToMany(mappedBy = "mainServiceEntity",cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<SubServiceEntity> subServices = new ArrayList<>();

	    @Column(name = "mainServiceName", unique = true, nullable = false)
	    private String mainServiceName;

	    @Column(name = "mainServiceShortName")
	    private String mainServiceShortName;

	    @Column(name = "governmentSection")
	    private String governmentSection;

	    @Column(name = "liable")
	    private String liable;

	    @Column(name = "prosAndCons")
	    private String prosAndCons;

	    @Column(name = "description", length = 500)
	    private String description;

	    @Column(name = "status")
	    private boolean status;

	   @ElementCollection
	   @CollectionTable(name = "mainServiceFilePaths", joinColumns = @JoinColumn(name = "mainServiceId"))
	   @Column(name = "mainServiceFilePaths")
	   private List<String> mainServiceFilePaths;


	    @Column(name = "createdBy")
	    private String createdBy;

	    @Column(name = "createdDate")
	    private LocalDate createdDate;

	    @Column(name = "updatedDate")
	    private LocalDate updatedDate;


		public MainServiceEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
 
	    

}
