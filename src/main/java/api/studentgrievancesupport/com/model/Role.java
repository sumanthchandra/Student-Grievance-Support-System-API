package api.studentgrievancesupport.com.model;

public enum Role {
	STUDENT("Student"), MEMBER("Member");

	private String value;

	public String getValue() {
		return this.value;
	}

	private Role(String value) {
		this.value = value;
	}

}
