package com.supermarkets.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarkets.controllers.AdminController;
import com.supermarkets.models.Supermarket;
import com.supermarkets.services.IAdminstrationService;
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)

public class AdminControllerTest {
	@Autowired
    ObjectMapper objectMapper;
	@Autowired
	MockMvc mockMvc;
	@MockBean
	IAdminstrationService adminstrationService;
	
	List<Supermarket> supermarkets = new ArrayList<Supermarket>();
	Supermarket s1= new Supermarket(1,"1طلبات","talabat1","44 kerstal palace mall",0);
	Supermarket s2= new Supermarket(2,"2طلبات","talabat2","45 kerstal palace mall",1);
	Supermarket s3= new Supermarket(3,"3طلبات","talabat3","46 kerstal palace mall",0);
	Supermarket s4= new Supermarket(4,"4طلبات","talabat4","447 kerstal palace mall",1);
	
	 @BeforeEach
	    public void setup(){
		 supermarkets.add(s1);
		 supermarkets.add(s2);
		 supermarkets.add(s3);
		 supermarkets.add(s4);	 
	 }
	
	@Test
	public void getAllSupermarkets_Success() throws Exception {	
		Mockito.when(adminstrationService.getAllSupermarkets()).thenReturn(supermarkets);
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/admin/api/supermarkets")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(4)))
                .andExpect(jsonPath("$[2].englishName", is("talabat3")))
	            ;
	}	
	@Test
	public void getSupermarketByID_Success() throws Exception {	
		Mockito.when(adminstrationService.getSupermarketById(s1.getId())).thenReturn((s1));
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/admin/api/supermarket?id=1")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.englishName", is("talabat1")));
	}
	
	@Test
	public void activateSupermarket_Success() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders
		            .put("/admin/api/supermarket/activate?id=1")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isCreated());
	
	}
	@Test
	public void deactivateSupermarket_Success() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders
		            .put("/admin/api/supermarket/deactivate?id=1")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isCreated());	
	}
	
	@Test
	public void deleteSupermarketById_success() throws Exception {
	    Mockito.when(adminstrationService.getSupermarketById(s1.getId())).thenReturn(s1);

	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/admin/api/supermarket?id=1")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNoContent());
	}

}
