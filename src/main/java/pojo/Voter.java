package pojo;

import java.sql.Date;

public class Voter {
    private int voterId;
    private String name;
    private String lastName;
    // id | name   | lastName | email             | password  | status | role  | dob        |
    private String email;
    private String password;
    private boolean status;
    private String role;
    private Date dob;
    
	@Override
	public String toString() {
		return "Voter [voterId=" + voterId + ", name=" + name + ", lastName=" + lastName + ", email=" + email
				+ ", status=" + status + ", role=" + role + ", dob=" + dob + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getVoterId() {
		return voterId;
	}

	public Voter(int voterId, String name, String lastName, String email, String password, boolean status, String role,
			Date dob) {
		super();
		this.voterId = voterId;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.status = status;
		this.role = role;
		this.dob = dob;
	}
}
