package es.mabu.mr.security.jwt;

import java.io.Serializable;

/**
 * JwTResponse.
 */
public class JwtAuthenticationResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1250166508152483573L;

	/** The token. */
	private final String token;

	/**
	 * Instantiates a new jwt authentication response.
	 *
	 * @param token
	 *            the token
	 */
	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}
}
