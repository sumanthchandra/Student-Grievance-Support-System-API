package api.studentgrievancesupport.com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import api.studentgrievancesupport.com.model.Role;

@Entity
@Table(name = "user", schema = "grievance_support")
public class User {

	@Id
	@NotNull
	@Email
	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_role")
	private String userRole;

	public User() {
	}

	public User(String userEmail, String userPassword, Role userRole) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole.getValue();
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
