package com.pawan.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.Entity.CountryMaster;

public interface CountryRepo extends JpaRepository<CountryMaster, Serializable> {

}
