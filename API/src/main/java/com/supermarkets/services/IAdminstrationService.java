package com.supermarkets.services;

import java.util.List;

import com.supermarkets.models.Supermarket;

public interface IAdminstrationService {
	  List<Supermarket> getAllSupermarkets();
	  Supermarket getSupermarketById(int id);	  
	  Supermarket saveSupermarket(Supermarket supermarket);
	  Supermarket updateSupermarket(Supermarket supermarket,int supermarketId);
	  void activate(int supermarketId );
	  void deactivate(int supermarketId);
	  void deleteSupermarket(int supermarketId);

}
