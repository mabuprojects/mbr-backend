package es.mabu.mr.user.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.NotFoundException;
import es.mabu.mr.security.jwt.JwtUser;
import es.mabu.mr.user.exception.InvalidUserException;
import es.mabu.mr.user.role.Role;
import es.mabu.mr.user.role.RoleService;
import es.mabu.mr.user.user.UserService;

@RestController

@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@PreAuthorize("hasRole('EMPLOYEE')")
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public JwtEmployee getAuthenticatedEmployee(HttpServletRequest request) throws NotFoundException {
		JwtUser user = userService.getUserId(request);
		if (user == null) {
			throw new NotFoundException("user not found");
		}
		Employee employee = employeeService.findById(user.getId());
		if (employee == null) {
			throw new NotFoundException("Employee not found with id: " + user.getId());
		}

		return new JwtEmployee(user, employee);
	}

	@PreAuthorize("hasRole('USER_MANAGER')")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Employee createEmployee(@RequestBody EmployeeDto employee)
			throws DuplicatedInstanceException, InvalidUserException {
		return employeeService.createEmployee(employee);

	}

	@PreAuthorize("hasRole('USER_MANAGER')")
	@RequestMapping(method = RequestMethod.PATCH, value = "{id}", produces = "application/json")
	public Employee updateEmployee(@RequestBody EmployeeDto employee, @PathVariable Long id)
			throws DuplicatedInstanceException, InvalidUserException {
		return employeeService.updateEmployee(employee, id);

	}

	@PreAuthorize("hasRole('USER_MANAGER')")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@PreAuthorize("hasRole('USER_MANAGER')")
	@RequestMapping(method = RequestMethod.GET, value = "/roles", produces = "application/json")
	public List<Role> getEmployeeRoles() {
		return roleService.getRoles();
	}

}
