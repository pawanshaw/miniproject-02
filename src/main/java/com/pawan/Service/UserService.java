package com.pawan.Service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pawan.Entity.UserMaster;

@Service
public interface UserService {

	public Map<Integer, String> getAllCountry();
	public Map<Integer, String> findState(Integer countryId);
	public Map<Integer, String> findCity(Integer stateId);
	
	public boolean save(UserMaster userMaster);
	
	public String loginFormCheckStatus(String email,String password);
	
	public boolean changePassword(String email,String newPassword);
	
	public boolean unlockinAccount(String email,String tempPAssword,String newPassword);
	
	public boolean forgotPassword(String email);
}
