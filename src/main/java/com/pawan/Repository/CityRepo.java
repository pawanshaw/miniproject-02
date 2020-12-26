package com.pawan.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.Entity.CityMaster;

public interface CityRepo extends JpaRepository<CityMaster, Serializable> {
	public List<CityMaster> findBystateId(Integer stateId);
}
