package es.mabu.mr.security.jwt;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import es.mabu.mr.user.role.Role;
import es.mabu.mr.user.user.User;

/**
 * A factory for creating JwtUser objects.
 */
public final class JwtUserFactory {

	/**
	 * Instantiates a new jwt user factory.
	 */
	private JwtUserFactory() {
	}

	/**
	 * Creates the.
	 *
	 * @param user
	 *            the user
	 * @return the jwt user
	 */
	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()),
				user.isEnabled());
	}

	/**
	 * Gets the authorities.
	 *
	 * @param roles
	 *            the roles
	 * @return the authorities
	 */
	private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	/**
	 * Gets the privileges.
	 *
	 * @param roles
	 *            the roles
	 * @return the privileges
	 */
	private static List<String> getPrivileges(Collection<Role> roles) {
		// @formatter:off
		List<String> privileges = roles.stream()
				.flatMap(role -> role.getPrivileges().stream()
						.map(privilege -> privilege.getName()))
				.collect(Collectors.toList());
		// @formatter:on
		return privileges;
	}

	/**
	 * Gets the granted authorities.
	 *
	 * @param privileges
	 *            the privileges
	 * @return the granted authorities
	 */
	private static List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		return privileges.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
}
