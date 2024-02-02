package com.example.efilingbazaar.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="SubserviceRegister")
public class SubServiceEntity {
	
	    @Id
        @Column(name = "subServiceId")
	    private String subServiceId; 

	    @ManyToOne
	    @JoinColumn(name = "mainServiceId", nullable = false)
	    private MainServiceEntity mainServiceEntity;

	    @Column(name = "subServiceName")
	    private String subServiceName;

	    @Column(name = "subServiceShortName")
	    private String subServiceShortName;

	    @Column(name = "description", length = 800)
	    private String description;

	    @Column(name = "governmentSection")
	    private String governmentSection;
	    
	    @Column(name = "liable")
	    private String liable;

	    @Column(name = "ProsAndCons")
	    private String ProsAndCons;

	    @Column(name = "status")
	    private boolean status;

	   /* @Lob
	    @Column(name = "fileData",columnDefinition = "longblob", nullable = false)
	    private byte[] fileData;*/

	  @ElementCollection
	  @CollectionTable(name = "subServiceFilePaths", joinColumns = @JoinColumn(name = "subServiceId"))
	  @Column(name = "subServiceFilePaths")
	  private List<String> subServiceFilePaths;

    	@Column(name = "fileType")
	    private String fileType;

	    @Column(name = "createdBy")
	    private String createdBy;

	    @Column(name = "createdDate")
	    private LocalDate createdDate;

	    @Column(name = "updatedDate")
	    private LocalDate updatedDate;


		public SubServiceEntity() {
			super();
			// TODO Auto-generated constructor stub
		}  

}
