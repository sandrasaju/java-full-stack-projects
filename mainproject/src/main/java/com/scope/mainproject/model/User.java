package com.scope.mainproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Project")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(name="firstname")
@NotBlank(message="First Name is requried")
@Size(min=2,max=50,message="Name must be between 2 to 50 characters")
private String firstname;

@Column(name="lastname")
@NotBlank(message="Last Name is requried")
@Size(min=1,max=50,message="Name must be between 2 to 20 characters")
private String lastname;

@Column(name="gender")
@NotBlank(message="Gender is requried")
private String gender;

@Column(name="dateofbirth")
private String dateofbirth;

@Column(name="email")
@NotBlank(message="Email is requried")
@Email(message="Invalid format")
private String email;

@Column(name="phonenumber")
//@NotEmpty(message="Phone Number is required")
//@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",message="Mobile nuumber is invalid")
private String phonenumber;

@Column(name="address")
@NotBlank(message="Address is requried")
private String address;

@ManyToOne
@JoinColumn(name="country")
//@NotBlank(message="Country is requried")
private Country country;

@ManyToOne
@JoinColumn(name="state")
//@NotBlank(message="State is requried")
private State state;
@ManyToOne
@JoinColumn(name="city")
//@NotBlank(message="City is requried")
private City city;
@Column(name="hobbies")
private String hobbies;

@Column(name="skills")
private String skills;

@Column(name="verificationcode")
private String verificationcode;

@Column(name="otp")
private String otp;

@Column(name="verified")
private boolean verified;
 
@Column(name="enabled")
private boolean enabled;

@ Column(name="password")
private String password;

@Column(name="newpassword")
private String newpassword;

@Column(name="course")
private String course;
public String getCourse() {
	return course;
}
public void setCourse(String course) {
	this.course = course;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getDateofbirth() {
	return dateofbirth;
}

public void setDateofbirth(String dateofbirth) {
	this.dateofbirth = dateofbirth;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhonenumber() {
	return phonenumber;
}

public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public Country getCountry() {
	return country;
}

public void setCountry(Country country) {
	this.country = country;
}

public State getState() {
	return state;
}

public void setState(State state) {
	this.state = state;
}

public City getCity() {
	return city;
}

public void setCity(City city) {
	this.city = city;
}

public String getHobbies() {
	return hobbies;
}

public void setHobbies(String hobbies) {
	this.hobbies = hobbies;
}

public String getSkills() {
	return skills;
}

public void setSkills(String skills) {
	this.skills = skills;
}


public String getVerificationcode() {
	return verificationcode;
}

public void setVerificationcode(String verificationcode) {
	this.verificationcode = verificationcode;
}

public String getOtp() {
	return otp;
}

public void setOtp(String otp) {
	this.otp = otp;
}

public boolean isVerified() {
	return verified;
}

public void setVerified(boolean verified) {
	this.verified = verified;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getNewpassword() {
	return newpassword;
}

public void setNewpassword(String newpassword) {
	this.newpassword = newpassword;
}

public User(int id, String firstname, String lastname, String gender, String dateofbirth, String email,
		String phonenumber, String address, Country country, State state, City city, String hobbies, String skills,
		String verificationcode, String otp, boolean verified, boolean enabled, String password,
		String newpassword) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.gender = gender;
	this.dateofbirth = dateofbirth;
	this.email = email;
	this.phonenumber = phonenumber;
	this.address = address;
	this.country = country;
	this.state = state;
	this.city = city;
	this.hobbies = hobbies;
	this.skills = skills;
	this.verificationcode = verificationcode;
	this.otp = otp;
	this.verified = verified;
	this.enabled = enabled;
	this.password = password;
	this.newpassword = newpassword;
}

public User() {
	super();
	// TODO Auto-generated constructor stub
}


}
