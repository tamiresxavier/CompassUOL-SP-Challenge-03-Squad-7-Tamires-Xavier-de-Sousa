package br.com.compassuol.pb.challenge.products.dtos;

import java.util.Set;

public class UserDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Set<Long> rolesIds;
	
	public UserDTO() {
	
	}

	public UserDTO(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Long> getRolesIds() {
		return rolesIds;
	}

	public void setRolesIds(Set<Long> rolesIds) {
		this.rolesIds = rolesIds;
	}
	
}