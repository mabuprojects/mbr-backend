package es.mabu.mr.user.client;

import es.mabu.mr.address.Address;
import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.user.exception.InvalidUserException;

public interface ClientService {

	/**
	 * Creates the new user.
	 *
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @return the user
	 * @throws InvalidUserException
	 *             the invalid user exception
	 * @throws DuplicatedInstanceException
	 *             the duplicated instance exception
	 */
	Client createClient(String email, String password) throws InvalidUserException, DuplicatedInstanceException;

	Client findClientById(Long id);

	Client setClientAddress(Long clientId, Address address);

}
