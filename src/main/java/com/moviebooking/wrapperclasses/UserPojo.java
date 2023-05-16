package com.moviebooking.wrapperclasses;

public class UserPojo {
	private String loginId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String contactNumber;
	private String jwtToken;

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

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public UserPojo() {
	}

	public UserPojo(String loginId, String firstName, String lastName, String emailId, String contactNumber,
			String jwtToken) {
		super();
		this.loginId = loginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.jwtToken = jwtToken;
	}

	@Override
	public String toString() {
		return "UserPojo [loginId=" + loginId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", contactNumber=" + contactNumber + ", jwtToken=" + jwtToken + "]";
	}
}
