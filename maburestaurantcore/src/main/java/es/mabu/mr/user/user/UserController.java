package es.mabu.mr.user.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.mabu.mr.security.jwt.JwtTokenUtil;
import es.mabu.mr.security.jwt.JwtUser;

/**
 * The Class UserController.
 */
@RestController
public class UserController {

	/** The token header. */
	@Value("${jwt.header}")
	private String tokenHeader;

	/** The jwt token util. */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Gets the authenticated user.
	 *
	 * @param request
	 *            the request
	 * @return the authenticated user
	 */
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public JwtUser getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		return user;
	}

}
