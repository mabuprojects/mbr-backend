package es.mabu.mr.security.jwt;

import java.io.Serializable;

/**
 * The JwtAuthenticationRequest.
 */
public class JwtAuthenticationRequest implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8445943548965154778L;

	/** The username. */
	private String username;

	/** The password. */
	private String password;

	/**
	 * Instantiates a new jwt authentication request.
	 */
	public JwtAuthenticationRequest() {
		super();
	}

	/**
	 * Instantiates a new jwt authentication request.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 */
	public JwtAuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
