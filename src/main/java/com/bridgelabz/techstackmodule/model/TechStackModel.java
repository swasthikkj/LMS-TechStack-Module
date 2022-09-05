package com.bridgelabz.techstackmodule.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bridgelabz.techstackmodule.dto.TechStackDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tech_stack")
@Data
@NoArgsConstructor
public class TechStackModel {
	@Id
	@GenericGenerator(name = "tech_stack", strategy = "increment")
	@GeneratedValue(generator = "tech_stack")	
	private long id;
	@JsonIgnore
	private LocalDateTime creatorStamp;
	private String imagePath;
	private boolean status;
	private String techName;
	
	public TechStackModel(TechStackDTO techStackDTO) {
		this.imagePath = techStackDTO.getImagePath();
		this.status = techStackDTO.isStatus();
		this.techName = techStackDTO.getTechName();
	}
}
