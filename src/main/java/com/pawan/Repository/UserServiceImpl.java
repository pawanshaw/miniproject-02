package com.pawan.Repository;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawan.Entity.CityMaster;
import com.pawan.Entity.CountryMaster;
import com.pawan.Entity.StateMaster;
import com.pawan.Entity.UserMaster;
import com.pawan.Service.UserService;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryRepo cr;
	@Autowired
	private StateRepo sr;
	@Autowired
	private CityRepo cityrep;
	@Autowired
	private UserRepo ur;
	
	
	@Override
	public Map<Integer, String> getAllCountry() {
		List<CountryMaster> countriesList = cr.findAll();
		Map<Integer, String> countries = new HashMap<>();
		countriesList.forEach(country -> {
			countries.put(country.getCountryId(), country.getCountryName());
		});
		
		return countries;
	}

	@Override
	public Map<Integer, String> findState(Integer countryId) {
		List<StateMaster> stateList = sr.findBycountryId(countryId);
		Map<Integer, String> states = new HashMap<>();
		stateList.forEach(state -> {
			states.put(state.getCountryId(), state.getStateName());
		});
		return states;
	}

	@Override
	public Map<Integer, String> findCity(Integer stateId) {
		 List<CityMaster> cityList = cityrep.findBystateId(stateId);
		 Map<Integer, String> cities = new HashMap<>();
		 cityList.forEach(city -> {
			 cities.put(city.getCityId(), city.getCityName());
		 });
		 return cities;
	}

	@Override
	public boolean save(UserMaster userMaster) {
		userMaster.setPassword(generateRandomPassword());
		userMaster.setAccountStatus("LOCKED");
		UserMaster save = ur.save(userMaster);
		return save.getUserId() != null;
	}
	
	public static String generateRandomPassword()
    {
		int len = 10;
        // ASCII range - alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of loop choose a character randomly from the given ASCII range
        // and append it to StringBuilder instance
 
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }

	@Override
	public String loginFormCheckStatus(String email, String password) {
		UserMaster userMaster = ur.findByemail(email);
		if(userMaster!=null) {
			if(userMaster.getAccountStatus().equals("UNLOCKED")) {
				if(userMaster.getPassword().equals(password)) {
					return "Hi "+userMaster.getFirstName()+" Welcome Back!";
				}else{
					return "Invalid Passowrd";
				}
			}else {
				return "Account is Locked!";
			}
		}else {
			return "User Dosent Exists!";
		}
	}

	@Override
	public boolean changePassword(String email, String newPassword) {
		UserMaster changePassword = ur.findByEmailAndPassword(email, newPassword);
		return changePassword!=null;
	}

	@Override
	public boolean unlockinAccount(String email, String tempPAssword,String newPassword) {
		UserMaster um = ur.findByEmailAndPassword(email,tempPAssword);
		if(um!=null) {
			um.setAccountStatus("UNLOCKED");
			um.setPassword(newPassword);
			ur.save(um);
			return true;
		}else {
			return false;
		}
	}

	public boolean forgotPassword(String email) {
		UserMaster findUserByEmail = ur.findByemail(email);
		return findUserByEmail != null;
	}

}
