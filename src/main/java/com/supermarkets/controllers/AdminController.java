package com.supermarkets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarkets.exceptions.InternalServerError;
import com.supermarkets.exceptions.ResourceNotFoundException;
import com.supermarkets.models.Supermarket;
import com.supermarkets.services.IAdminstrationService;

@RestController
@RequestMapping("/admin/api")
public class AdminController {

	@Autowired
	private IAdminstrationService adminstrationService;

	@GetMapping("/supermarkets")
	private ResponseEntity<List<Supermarket>> getAllSupermarkets() {

		List<Supermarket> allSupermarkets = adminstrationService.getAllSupermarkets();
		if (allSupermarkets != null)
			return new ResponseEntity<>(allSupermarkets, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping("/supermarket")
	private ResponseEntity<Supermarket> saveSupermarket(@RequestBody Supermarket supermarket) {
		try {
			return new ResponseEntity<>(adminstrationService.saveSupermarket(supermarket), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	@PutMapping("/supermarket/activate")
	private ResponseEntity<HttpStatus> activateSuperMarket(@RequestParam("id") int supermarketId) {
		try {
			adminstrationService.activate(supermarketId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	@PutMapping("/supermarket/deactivate")
	private ResponseEntity<HttpStatus> deactivateSuperMarket(@RequestParam("id") int supermarketId) {
		try {
			adminstrationService.deactivate(supermarketId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}
	
	@DeleteMapping("/supermarket")
	private ResponseEntity<String> deleteSupermarket(@RequestParam("id") int supermarketId) {
		try {
			adminstrationService.deleteSupermarket(supermarketId);
			return new ResponseEntity<>("Successfully deleted : "+supermarketId,HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResourceNotFoundException("supermarket not found for this id :: " + supermarketId);
		}

	}
	
	
	
	
	
	
}
