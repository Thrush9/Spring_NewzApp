package com.stackroute.userprofile.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  
 */
@Document
public class UserProfile {

	/*
	 * This class should have six fields (userId,firstName,
	 * lastName,contact,email,createdAt). Out of these six fields, the field userId
	 * should be annotated with @Id (This annotation explicitly specifies the
	 * document identifier). This class should also contain the getters and setters
	 * for the fields, along with the no-arg , parameterized constructor and
	 * toString method.The value of createdAt should not be accepted from the user
	 * but should be always initialized with the system date.
	 */

	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String contact;
	private String email;
	private LocalDateTime createdAt;

	public UserProfile() {
	}

	public UserProfile(String string1, String string2, String string3, String string4, String string5,
			LocalDateTime date) {
		this.userId = string1;
		this.firstName = string2;
		this.lastName = string3;
		this.contact = string4;
		this.email = string5;
		this.createdAt = date;

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", contact="
				+ contact + ", email=" + email + ", createdAt=" + createdAt + "]";
	}

	
}
