package api.studentgrievancesupport.com.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "members", schema = "grievance_support")
public class GrievanceMember implements Serializable {
	
	@Id
	@Column(name = "memail")
	private String email;
	
	@Column(name = "jurisdiction")
	private String jurisdiction;
	
	@Column(name = "mcollege")
	private String college;
	
	@Column(name = "muniversity")
	private String university;
	
	@Column(name = "mfirstname")
	private String firstName;
	
	@Column(name = "mlastname")
	private String lastName;
	
	
	@Column(name = "mpassword")
	private String password;
	
	@Column(name = "mgender")
	private String gender;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "memail", nullable = false)
    private User user;
	
	public GrievanceMember() {	}	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
