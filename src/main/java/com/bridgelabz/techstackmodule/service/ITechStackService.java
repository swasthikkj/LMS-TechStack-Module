package com.bridgelabz.techstackmodule.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.techstackmodule.dto.TechStackDTO;
import com.bridgelabz.techstackmodule.model.TechStackModel;

public interface ITechStackService {
	TechStackModel addTechStack(TechStackDTO techStackDTO, String token);
	
	TechStackModel updateTechStack(TechStackDTO techStackDTO, Long id, String token);
	
	Optional<TechStackModel> getTechById(Long id, String token);

	List<TechStackModel> getAllTechStacks(String token);

	TechStackModel deleteTechStack(Long id, String token);

}
