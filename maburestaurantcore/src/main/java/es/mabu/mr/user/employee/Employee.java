package es.mabu.mr.user.employee;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import es.mabu.mr.user.user.User;

@Entity
public class Employee extends User {
	public Employee() {
		super();
	}

	public Employee(User user) {
		super(user);
	}

	@NotEmpty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}