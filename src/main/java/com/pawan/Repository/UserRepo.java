package com.pawan.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.Entity.UserMaster;

public interface UserRepo extends JpaRepository<UserMaster, Serializable> {
	
	//public boolean findByemail(String email);
	
	public UserMaster findByemail(String email);
	
	//public UserMaster findByunlockAccount(String email,String password);
	
	public UserMaster findByEmailAndPassword(String email,String password);

}
