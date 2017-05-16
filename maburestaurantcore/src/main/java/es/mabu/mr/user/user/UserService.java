package es.mabu.mr.user.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.security.jwt.JwtUser;
import es.mabu.mr.user.exception.InvalidUserException;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * populates an user Instance
	 * 
	 * @param email
	 * @param password
	 * @param roles
	 * @return
	 * @throws DuplicatedInstanceException
	 */
	User populateUser(User user, String email, String password, List<Long> roles)
			throws InvalidUserException, DuplicatedInstanceException;

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return the user
	 */
	User findByEmail(String email);

	boolean emailExists(String email);

	User updateUser(User user, String email, List<Long> roles) throws InvalidUserException, DuplicatedInstanceException;

	JwtUser getUserId(HttpServletRequest request);
}
