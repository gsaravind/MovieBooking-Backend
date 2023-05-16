package com.moviebooking.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {
	@Id
	private String loginId;

	@NotBlank(message = "FirstName can't be blank")
	private String firstName;
	@NotBlank(message = "LastName can't be blank")
	private String lastName;
	@NotBlank(message = "Password can't be blank")
	private String password;
	@NotBlank(message = "Email Id can't be blank")
	private String emailId;
	@NotBlank(message = "Contact Number can't be blank")
	private String contactNumber;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> role;

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public User() {
	}

	public User(String loginId, String firstName, String lastName, String password, String emailId,
			String contactNumber, Set<Role> role) {
		this.loginId = loginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.role = role;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "User [loginId=" + loginId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", emailId=" + emailId + ", contactNumber=" + contactNumber + ", role=" + role + "]";
	}

}
