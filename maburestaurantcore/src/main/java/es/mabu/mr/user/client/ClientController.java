package es.mabu.mr.user.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.mabu.mr.address.Address;
import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.security.jwt.JwtTokenUtil;
import es.mabu.mr.security.jwt.JwtUser;
import es.mabu.mr.user.exception.InvalidUserException;
import es.mabu.mr.user.user.UserDto;

@RestController
public class ClientController {

	/** The user service. */
	@Autowired
	private ClientService clientService;

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
	 * Creates the user.
	 *
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @return the string
	 * @throws InvalidUserException
	 *             the invalid user exception
	 * @throws DuplicatedInstanceException
	 *             the duplicated instance exception
	 */
	@RequestMapping(value = "/public/user", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String createUser(@RequestBody UserDto user) throws InvalidUserException, DuplicatedInstanceException {
		clientService.createClient(user.getEmail(), user.getPassword());
		return "User succesfully created";
	}

	@PreAuthorize("hasRole('CLIENT')")
	@RequestMapping(value = "/client/address", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public Client setClientAddress(@RequestBody Address address, HttpServletRequest request) {

		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

		return clientService.setClientAddress(user.getId(), address);
	}

	@PreAuthorize("hasRole('CLIENT')")
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Client getCLient(HttpServletRequest request) {

		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

		return clientService.findClientById(user.getId());
	}

}
