package es.mabu.mr.user.employee;

import java.util.List;

import es.mabu.mr.user.user.UserDto;

public class EmployeeDto extends UserDto {

	private String name;
	private List<Long> roles;

	public EmployeeDto() {

	}

	public EmployeeDto(String email, String password, String name, List<Long> roles) {
		super(email, password);
		this.name = name;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public List<Long> getRoles() {
		return roles;
	}

}
