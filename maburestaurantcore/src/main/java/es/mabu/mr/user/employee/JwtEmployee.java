package es.mabu.mr.user.employee;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import es.mabu.mr.security.jwt.JwtUser;

public class JwtEmployee extends JwtUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5471105401453261332L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JwtEmployee(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities,
			boolean enabled) {
		super(id, email, password, authorities, enabled);

	}

	public JwtEmployee(JwtUser user, Employee employee) {
		super(user.getId(), user.getEmail(), "", user.getAuthorities(), user.isEnabled());
		this.name = employee.getName();
	}

}
