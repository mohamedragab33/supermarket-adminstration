package com.supermarkets.services;

import java.util.List;
import java.util.Objects;
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

		Supermarket supDB = adminstrationRepository.findById(supermarketId).get();
  
        if (Objects.nonNull(supermarket.getArabicName()) && !"".equalsIgnoreCase(supermarket.getArabicName())) {
        	supDB.setArabicName(supermarket.getArabicName());
        }
        if (Objects.nonNull(supermarket.getEnglishName()) && !"".equalsIgnoreCase(supermarket.getEnglishName())) {
        	supDB.setEnglishName(supermarket.getEnglishName());
        }
        if (Objects.nonNull(supermarket.getAddress()) && !"".equalsIgnoreCase(supermarket.getAddress())) {
        	supDB.setAddress(supermarket.getAddress());
        }
        if (Objects.nonNull(supermarket.getImage()) && !"".equalsIgnoreCase(supermarket.getImage())) {
        	supDB.setImage(supermarket.getImage());
        }                
        if (Objects.nonNull(supermarket.getActive())) {
        	supDB.setActive(supermarket.getActive());
        }
        
        return adminstrationRepository.save(supDB);
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
