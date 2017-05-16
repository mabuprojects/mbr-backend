package es.mabu.mr.user;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.user.employee.Employee;
import es.mabu.mr.user.employee.EmployeeDto;
import es.mabu.mr.user.employee.EmployeeService;
import es.mabu.mr.user.exception.InvalidUserException;
import es.mabu.mr.user.role.RoleService;
import es.mabu.mr.user.user.User;
import es.mabu.mr.user.user.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class EmployeeServiceTest {

	private final static String EMAIL = "EMAIL1@gmail.com";

	private final static String PASSWORD = "PASSWORD";

	@Autowired
	EmployeeService employeeService;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Test
	public void CreateUser() throws InvalidUserException, DuplicatedInstanceException {
		List<Long> roles = new ArrayList<>();
		roles.add(2L);
		roles.add(3L);
		EmployeeDto employee = new EmployeeDto(EMAIL, PASSWORD, "Paco", roles);

		Employee user = employeeService.createEmployee(employee);

		User userFound = userService.findByEmail(user.getEmail());

		assertEquals(user, userFound);
	}

	@Test(expected = DuplicatedInstanceException.class)
	public void CreateUserSameEmail() throws InvalidUserException, DuplicatedInstanceException {

		List<Long> roles = new ArrayList<>();
		roles.add(2L);
		EmployeeDto employee = new EmployeeDto(EMAIL, PASSWORD, "Paco", roles);

		User user = employeeService.createEmployee(employee);

		EmployeeDto repeatedEmployee = new EmployeeDto(EMAIL, PASSWORD, "Paco", roles);
		User repeatedUser = employeeService.createEmployee(repeatedEmployee);
		assertEquals(user, repeatedUser);
	}

}
