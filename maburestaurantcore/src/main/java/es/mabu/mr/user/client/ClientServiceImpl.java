package es.mabu.mr.user.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.address.Address;
import es.mabu.mr.address.AddressRepository;
import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.user.exception.InvalidUserException;
import es.mabu.mr.user.role.Role;
import es.mabu.mr.user.role.RoleService;
import es.mabu.mr.user.user.UserService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.mabu.mr.user.UserService#createNewUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Client createClient(String email, String password) throws InvalidUserException, DuplicatedInstanceException {

		Client client = new Client();

		Role role = roleService.findByName("ROLE_USER");
		userService.populateUser(client, email, password, Arrays.asList(role.getId()));
		return clientRepository.save(client);

	}

	public Client setClientAddress(Long clientId, Address address) {
		Client client = clientRepository.findOne(clientId);
		client.setAddress(addressRepository.save(address));
		return clientRepository.save(client);
	}

	@Override
	public Client findClientById(Long id) {
		return clientRepository.findOne(id);
	}
}
