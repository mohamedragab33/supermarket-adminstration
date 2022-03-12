package com.supermarkets.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.supermarkets.models.Supermarket;
import com.supermarkets.repository.AdminstrationRepository;

@SpringBootTest
public class AdminstrationServiceTest {
	@MockBean
	AdminstrationRepository adminstrationRepository;
	@Autowired
	AdminstrationServiceImp adminstrationService;

	List<Supermarket> supermarkets = new ArrayList<Supermarket>();
	Supermarket s1 = new Supermarket(1, "1طلبات", "talabat1", "44 kerstal palace mall", 0);
	Supermarket s2 = new Supermarket(2, "2طلبات", "talabat2", "45 kerstal palace mall", 1);
	Supermarket s3 = new Supermarket(3, "3طلبات", "talabat3", "46 kerstal palace mall", 0);
	Supermarket s4 = new Supermarket(4, "4طلبات", "talabat4", "447 kerstal palace mall", 1);

	@BeforeEach
	public void setup() {
		supermarkets.add(s1);
		supermarkets.add(s2);
		supermarkets.add(s3);
		supermarkets.add(s4);
	}

	@Test
	public void GetAllSupermarkets_itShouldReturnAllSupermarkets() {
		when(adminstrationRepository.findAll()).thenReturn(supermarkets);
		assertThat(adminstrationService.getAllSupermarkets().size()).isEqualTo(4);
	}

}
