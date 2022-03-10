package com.supermarkets.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarkets.models.Supermarket;
import com.supermarkets.repository.AdminstrationRepository;

@Service
public class AdminstrationServiceImp implements IAdminstrationService {
	@Autowired
	AdminstrationRepository adminstrationRepository;

	@Override
	public List<Supermarket> getAllSupermarkets() {
		List<Supermarket> supermarkets = adminstrationRepository.findAll();
		return supermarkets;
	}

	@Override
	public Supermarket getSupermarketById(int id) {
		Optional<Supermarket> supermarketResponse =  adminstrationRepository.findById(id);
		Supermarket supermarket = supermarketResponse.get();
		return supermarket;
	}

	@Override
	public Supermarket updateSupermarket(Supermarket supermarket, int supermarketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supermarket saveSupermarket(Supermarket supermarket) {
		  return adminstrationRepository.save(supermarket);
	}

	@Override
	public void activate(int supermarketId) {
		adminstrationRepository.activate(supermarketId);
	}

	@Override
	public void deactivate(int supermarketId) {
		adminstrationRepository.deactivate(supermarketId);
	}

	@Override
	public void deleteSupermarket(int supermarketId) {
		adminstrationRepository.deleteById(supermarketId);		
	}

}
