package es.mabu.mr.user;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.user.client.ClientService;
import es.mabu.mr.user.exception.InvalidUserException;
import es.mabu.mr.user.privilege.Privilege;
import es.mabu.mr.user.privilege.PrivilegeRepository;
import es.mabu.mr.user.role.Role;
import es.mabu.mr.user.role.RoleService;
import es.mabu.mr.user.user.User;
import es.mabu.mr.user.user.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class ClientServiceTest {

	private final static String EMAIL = "EMAIL1@gmail.com";

	private final static String PASSWORD = "PASSWORD";

	@Autowired
	ClientService clientService;

	@Autowired
	UserService userService;

	@Autowired
	PrivilegeRepository pR;

	@Autowired
	RoleService rS;

	@Before
	public void Init() {
		Privilege p = pR.save(new Privilege("ROLE_USER"));
		rS.createNewRole(new Role("ROLE_USER", Arrays.asList(p)));
	}

	@Test
	public void CreateUser() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient(EMAIL, PASSWORD);

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);

	}

	@Test(expected = InvalidUserException.class)
	public void CreateUserNullEmail() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient(null, PASSWORD);

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);
	}

	@Test(expected = InvalidUserException.class)
	public void CreateUserEmptyEmail() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient("", PASSWORD);

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);
	}

	@Test(expected = InvalidUserException.class)
	public void CreateUserEmptyPassword() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient(EMAIL, "");

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);
	}

	@Test(expected = InvalidUserException.class)
	public void CreateUserNullPassword() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient(EMAIL, null);

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);
	}

	@Test(expected = InvalidUserException.class)
	public void CreateUserShortPassword() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient(EMAIL, "1234");

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);
	}

	@Test(expected = InvalidUserException.class)
	public void CreateUserShortPasswordWithSpaces() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient(EMAIL, "  123456");

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);
	}

	@Test()
	public void CreateUserPasswordWithSpaces() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient(EMAIL, "12 34 56");

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);
	}

	@Test(expected = DuplicatedInstanceException.class)
	public void CreateUserSameEmail() throws InvalidUserException, DuplicatedInstanceException {

		User user = clientService.createClient(EMAIL, PASSWORD);
		User repeatedUser = clientService.createClient(EMAIL, PASSWORD);

		assertEquals(user, repeatedUser);
	}

}
