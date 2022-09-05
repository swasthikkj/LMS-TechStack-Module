package com.bridgelabz.techstackmodule.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.techstackmodule.util.Response;
import com.bridgelabz.techstackmodule.dto.TechStackDTO;
import com.bridgelabz.techstackmodule.model.TechStackModel;
import com.bridgelabz.techstackmodule.service.ITechStackService;
/**
 * Purpose:create tech stack controller
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */
@RestController
@RequestMapping("/techstackModule")
public class TechStackController {
	@Autowired
	ITechStackService techService;
	/**
	 * Purpose:add tech stack
	 * @Param token 
	 */
	@PostMapping("/addTechStack")
	public ResponseEntity<Response> addTechStack(@Valid @RequestBody TechStackDTO techStackDTO, @RequestHeader String token) {
		TechStackModel techStackModel = techService.addTechStack(techStackDTO, token);
		Response response = new Response(200, "techstack added successfully", techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	/**
	 * Purpose:update tech stack by id
	 * @Param token and id
	 */
	@PutMapping("updateTechStack/{id}")
	public ResponseEntity<Response> updateTechStack(@Valid @RequestBody TechStackDTO techStackDTO, @PathVariable Long id, @RequestHeader String token) {
		TechStackModel techStackModel = techService.updateTechStack(techStackDTO, id, token);
		Response response = new Response(200, "techstack updated successfully", techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:fetch tech stack by id
	 * @Param token and id
	 */
	@GetMapping("/getTechStack/{id}")
    public ResponseEntity<Response> getTechById(@PathVariable Long id, @RequestHeader String token) {
		Optional<TechStackModel> techStackModel = techService.getTechById(id, token);
		Response response = new Response(200, "techstack fetched by id successfully", techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
	/**
	 * Purpose:fetch all tech stacks
	 * @Param token 
	 */
	@GetMapping("/getAllTechStacks")
	public ResponseEntity<Response> getAllTechStacks(@RequestHeader String token) {
		List<TechStackModel> techStackModel = techService.getAllTechStacks(token);
		Response response = new Response(200, "All techstacks fetched successfully", techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	/**
	 * Purpose:delete tech stack by id
	 * @Param token and id
	 */
	@DeleteMapping("/deleteTechStack/{id}")
	public ResponseEntity<Response> deleteTechStack(@PathVariable Long id, @RequestHeader String token) {
		TechStackModel techStackModel = techService.deleteTechStack(id, token);
		Response response = new Response(200, "techstack deleted successfully", techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
