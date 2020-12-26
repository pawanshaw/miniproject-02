package com.pawan.restcontroller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pawan.Entity.UserMaster;
import com.pawan.Repository.UserServiceImpl;

@RestController
public class UserRestController {
	
	@Autowired
	private UserServiceImpl usi;
	
	@GetMapping(value = "/loadForm")
	public ResponseEntity<Map<Integer, String>> loadForm() {
		Map<Integer, String> allCountry = usi.getAllCountry();
		return ResponseEntity.ok(allCountry);
	}
	
	@GetMapping(value = "/getState/{countryId}")
	public ResponseEntity<Map<Integer, String>> getState(@PathVariable("countryId")Integer countryId) {
		Map<Integer, String> findState = usi.findState(countryId);
		return ResponseEntity.ok(findState);
	}
	
	@GetMapping(value = "/getCity/{stateId}")
	public ResponseEntity<Map<Integer, String>> getCity(@PathVariable("stateId")Integer stateId) {
		Map<Integer, String> findCity = usi.findCity(stateId);
		return ResponseEntity.ok(findCity);
	}
	
	
	@PostMapping(value = "/saveUser",
			consumes = "application/json"
			)
	public String saveUser(@RequestBody UserMaster  um) {
		boolean save = usi.save(um);
		if(save) {
			return "User Registration Successful";
		}else {
			return "Something Went Wrong! Please try again..";
		}
	}
	
	@PostMapping(value = "/login")
	public String login(@RequestBody UserMaster um) {
		String email = um.getEmail();
		String pass = um.getPassword();
		String loginFormCheckStatus = usi.loginFormCheckStatus(email, pass);
		return loginFormCheckStatus;
	}
	
	@GetMapping(value = "/unlock/{email}/{oldPassword}/{newPassword}")
	public String unlockAccount(@PathVariable("email")String email,@PathVariable("oldPassword")String oldPassword,@PathVariable("newPassword")String newPassword) {
		System.out.println("email : "+email);
		System.out.println("old password : "+oldPassword);
		System.out.println("new password : "+newPassword);
		boolean unlockinAccount = usi.unlockinAccount(email, oldPassword, newPassword);
		if(unlockinAccount) {
			return "Unlock Successfull";
		}else{
			return "Unlock UnSuccessfull";
		}
	}
	
	@GetMapping(value = "forgotPassword/{email}")
	public UserMaster resetPassword(@PathVariable("email") String email) {
		return null;
	}
	
	
}
