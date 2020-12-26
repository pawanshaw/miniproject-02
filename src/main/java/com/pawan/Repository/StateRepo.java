package com.pawan.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.Entity.StateMaster;

public interface StateRepo extends JpaRepository<StateMaster, Serializable> {
	
	public List<StateMaster> findBycountryId(Integer countryId);
}
