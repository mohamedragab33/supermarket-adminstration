package com.supermarkets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.supermarkets.models.Supermarket;

@Repository
public interface AdminstrationRepository extends  JpaRepository<Supermarket, Integer> {
	
	@Modifying(clearAutomatically = true)
	@Query("update Supermarket set Active=1  where Supermarket_Id =:id")
	void activate(@Param("id")int id);
	
	@Modifying(clearAutomatically = true)
	@Query("update Supermarket set Active=0  where Supermarket_Id =:id")
	@Transactional
	void deactivate(@Param("id")int id);
	

}
