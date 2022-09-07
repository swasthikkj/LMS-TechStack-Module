package com.bridgelabz.techstackmodule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.techstackmodule.dto.TechStackDTO;
import com.bridgelabz.techstackmodule.exception.CustomNotFoundException;
import com.bridgelabz.techstackmodule.model.TechStackModel;
import com.bridgelabz.techstackmodule.repository.TechStackRepository;
import com.bridgelabz.techstackmodule.util.TokenUtil;

@Service
public class TechStackService implements ITechStackService {
	@Autowired
	TechStackRepository techRepository;

	//	@Autowired
	//	TokenUtil tokenUtil;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public TechStackModel addTechStack(TechStackDTO techStackDTO, String token) {
		//		Long techId = tokenUtil.decodeToken(token);
		//		Optional<TechStackModel> isTokenPresent = techRepository.findById(techId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<TechStackModel> list = new ArrayList<>();
			//			adminId.stream().forEach(id -> {
			//				Optional<TechStackModel> isIdPresent = techRepository.findById(id);
			//				if (isIdPresent.isPresent()) {
			//					list.add(isIdPresent.get());
			//				}
			//			});
			TechStackModel model = new TechStackModel(techStackDTO);
			//			if(list.size() > 0) {
			//				model.setCreatorUser(list);
			//			}
			techRepository.save(model);
			return model;
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public TechStackModel updateTechStack(TechStackDTO techStackDTO, Long id, String token) {
		//		Long techId = tokenUtil.decodeToken(token);
		//		Optional<TechStackModel> isTokenPresent = techRepository.findById(techId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<TechStackModel> isTechPresent = techRepository.findById(id);
			if(isTechPresent.isPresent()) {
				isTechPresent.get().setTechName(techStackDTO.getTechName());
				isTechPresent.get().setImagePath(techStackDTO.getImagePath());
				isTechPresent.get().setStatus(techStackDTO.isStatus());
				techRepository.save(isTechPresent.get());
				return isTechPresent.get();
			}
			throw new CustomNotFoundException(400,"tech stack not present");
		}
		throw new CustomNotFoundException(400,"token invalid");
	}

	@Override
	public Optional<TechStackModel> getTechById(Long id, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			return techRepository.findById(id);
		}
		throw new CustomNotFoundException(400,"token invalid");
	}

	@Override
	public List<TechStackModel> getAllTechStacks(String token) {
		//		Long techId = tokenUtil.decodeToken(token);
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<TechStackModel> getAllTechStacks = techRepository.findAll();
			if(getAllTechStacks.size()>0) {
				return getAllTechStacks;
			} else {
				throw new CustomNotFoundException(400, "Tech stack not present");
			}
		}
		throw new CustomNotFoundException(400,"token invalid");
	}

	@Override
	public TechStackModel deleteTechStack(Long id, String token) {
		//		Long techId = tokenUtil.decodeToken(token);
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<TechStackModel> isTechPresent = techRepository.findById(id);
			if(isTechPresent.isPresent()) {
				techRepository.delete(isTechPresent.get());
				return isTechPresent.get();
			}
			throw new CustomNotFoundException(400, "Tech stack not found");
		}
		throw new CustomNotFoundException(400,"token invalid");
	}
}
