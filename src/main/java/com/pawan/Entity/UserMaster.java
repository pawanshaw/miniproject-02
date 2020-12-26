package com.pawan.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_DTL")
public class UserMaster {

	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE_NUMBER")
	private long phoneNumber;
	
	@Column(name="DOB")
	private String dob;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="COUNTRY")
	private Integer country;
	
	@Column(name="STATE")
	private Integer state;
	
	@Column(name="CITY")
	private Integer city;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;
	
}
