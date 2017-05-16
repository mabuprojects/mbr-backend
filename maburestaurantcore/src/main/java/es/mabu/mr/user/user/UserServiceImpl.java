package es.mabu.mr.user.user;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.security.jwt.JwtTokenUtil;
import es.mabu.mr.security.jwt.JwtUser;
import es.mabu.mr.user.exception.InvalidUserException;
import es.mabu.mr.user.role.Role;
import es.mabu.mr.user.role.RoleService;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The token header. */
	@Value("${jwt.header}")
	private String tokenHeader;

	/** The jwt token util. */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/** The role service. */
	@Autowired
	RoleService roleService;

	/**
	 * Validate email.
	 *
	 * @param email
	 *            the email
	 * @return the string
	 * @throws InvalidUserException
	 *             the invalid user exception
	 * @throws DuplicatedInstanceException
	 *             the duplicated instance exception
	 */
	private String validateEmail(String email) throws InvalidUserException, DuplicatedInstanceException {
		return this.validateEmail(email, null);

	}

	/**
	 * Validate email.
	 *
	 * @param email
	 *            the email
	 * @return the string
	 * @throws InvalidUserException
	 *             the invalid user exception
	 * @throws DuplicatedInstanceException
	 *             the duplicated instance exception
	 */
	private String validateEmail(String email, String original)
			throws InvalidUserException, DuplicatedInstanceException {

		if (email == null)
			throw new InvalidUserException("Email is null");

		InternetAddress emailAddr;
		try {
			emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException e) {
			throw new InvalidUserException("The email '" + email + "' is not a valid email");
		}
		if (this.emailExists(email) && (original == null || !email.equals(original))) {
			throw new DuplicatedInstanceException("The email" + email + "is already registered");
		}
		return email;

	}

	/**
	 * Validate password.
	 *
	 * @param password
	 *            the password
	 * @return the string
	 * @throws InvalidUserException
	 *             the invalid user exception
	 */
	private String validatePassword(String password) throws InvalidUserException {
		if (password == null)
			throw new InvalidUserException("Password is null");
		password = password.trim();
		if (password.length() < 8)
			throw new InvalidUserException("Password is too short at least 8 characters");
		return password;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.mabu.mr.user.UserService#findByEmail(java.lang.String)
	 */
	@Override
	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public User populateUser(User user, String email, String password, List<Long> rolesId)
			throws InvalidUserException, DuplicatedInstanceException {
		email = validateEmail(email);
		// Valid password
		password = validatePassword(password);

		user.setEmail(email);
		// Encode the password
		user.setPassword(new BCryptPasswordEncoder().encode(password));

		List<Role> roles = roleService.getRoles(rolesId);

		if (roles == null) {
			throw new InvalidUserException("Every user must have at least one role");
		}
		user.setRoles(roles);

		return user;

	}

	/**
	 * Email exists.
	 *
	 * @param email
	 *            the email
	 * @return true, if successful
	 */

	@Override
	public boolean emailExists(String email) {
		return this.findByEmail(email) != null;
	}

	@Override
	public User updateUser(User user, String email, List<Long> rolesId)
			throws InvalidUserException, DuplicatedInstanceException {
		email = validateEmail(email, user.getEmail());
		// Valid password

		user.setEmail(email);

		List<Role> roles = roleService.getRoles(rolesId);

		if (roles == null) {
			throw new InvalidUserException("Every user must have at least one role");
		}
		user.setRoles(roles);

		return user;

	}

	@Override
	public JwtUser getUserId(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return (JwtUser) userDetailsService.loadUserByUsername(username);
	}

}
