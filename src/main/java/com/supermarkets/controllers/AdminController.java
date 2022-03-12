package com.supermarkets.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarkets.exceptions.InternalServerError;
import com.supermarkets.exceptions.ResourceNotFoundException;
import com.supermarkets.models.Supermarket;
import com.supermarkets.services.IAdminstrationService;
import com.supermarkets.utils.FileUploadUtil;

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

	@GetMapping("/supermarket")
	private ResponseEntity<Supermarket> getSupermarket(@RequestParam("id") int supermarketID) {
		try {
			return new ResponseEntity<>(adminstrationService.getSupermarketById(supermarketID), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Supermarket not found for this id :: " + supermarketID);
		}

	}

	@PostMapping(value = "/supermarket", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	private ResponseEntity<Supermarket> saveSupermarket(
			@RequestPart(name = "image", required = false) MultipartFile multipartFile,
			@RequestPart("supermarket") String supermarketReq) {
		try {
			Supermarket supermarket = mapSupermarket(supermarketReq);
			return new ResponseEntity<>(saveSuperMarket(supermarket, multipartFile), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	@PutMapping(value = "/supermarket", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	private ResponseEntity<Supermarket> updateSupermarket(@RequestParam("id") int supermarketID,
			@RequestPart("supermarket") String supermarketReq,
			@RequestPart(name = "image", required = false) MultipartFile multipartFile) {
		try {
			Supermarket supermarket = mapSupermarket(supermarketReq);
			return new ResponseEntity<>(updateSupermarket(supermarket, multipartFile, supermarketID),
					HttpStatus.CREATED);

		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	@PutMapping(value = "/supermarket/activate", produces = "application/json")
	@ResponseBody
	private ResponseEntity<String> activateSuperMarket(@RequestParam("id") int supermarketId) {
		try {

			adminstrationService.activate(supermarketId);
			return new ResponseEntity<>("Activated : " + supermarketId, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	@PutMapping(value = "/supermarket/deactivate", produces = "application/json")
	private ResponseEntity<String> deactivateSuperMarket(@RequestParam("id") int supermarketId) {
		try {
			adminstrationService.deactivate(supermarketId);
			return new ResponseEntity<>("Deactivated : " + supermarketId, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	@DeleteMapping("/supermarket")
	private ResponseEntity<HttpStatus> deleteSupermarket(@RequestParam("id") int supermarketId) {
		try {

			adminstrationService.deleteSupermarket(supermarketId);
			FileUploadUtil.deleteFile("supermarket_photos/" + String.valueOf(supermarketId));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResourceNotFoundException("supermarket not found for this id :: " + supermarketId);
		}

	}

	public Supermarket mapSupermarket(String supermarket) {
		Supermarket conSupermarket = new Supermarket();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			conSupermarket = objectMapper.readValue(supermarket, Supermarket.class);
			return conSupermarket;
		} catch (IOException e) {
			throw new InternalServerError("Error on converting");
		}

	}

	public Supermarket saveSuperMarket(Supermarket supermarket, MultipartFile multipartFile) {
		try {
			Supermarket supermarketRes = new Supermarket();
			if (multipartFile != null && !multipartFile.isEmpty()) {
				String fileName = supermarket.getEnglishName().replaceAll("\\s+", "_") + ".jpg";
				supermarket.setImage(fileName);
				supermarketRes = adminstrationService.saveSupermarket(supermarket);
				String uploadDir = "supermarket_photos/" + supermarketRes.getId();
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} else
				supermarketRes = adminstrationService.saveSupermarket(supermarket);

			return supermarketRes;
		} catch (IOException e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	public Supermarket updateSupermarket(Supermarket supermarket, MultipartFile multipartFile, int supermarketID) {
		try {
			Supermarket supermarketRes = new Supermarket();
			if (multipartFile != null && !multipartFile.isEmpty()) {
				String fileName = supermarket.getEnglishName().replaceAll("\\s+", "_") + ".jpg";
				supermarket.setImage(fileName);
				supermarketRes = adminstrationService.updateSupermarket(supermarket, supermarketID);
				String uploadDir = "supermarket_photos/" + supermarketRes.getId();
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} else
				supermarketRes = adminstrationService.updateSupermarket(supermarket, supermarketID);

			return supermarketRes;
		} catch (IOException e) {
			throw new InternalServerError(e.getMessage());
		}
	}
}
