package es.mabu.mr.user.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.user.exception.InvalidUserException;
import es.mabu.mr.user.user.UserService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	UserService userService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Employee createEmployee(EmployeeDto employeeDto) throws InvalidUserException, DuplicatedInstanceException {
		Employee employee = new Employee();
		validateEmployee(employeeDto);
		userService.populateUser(employee, employeeDto.getEmail(), employeeDto.getPassword(), employeeDto.getRoles());
		employee.setName(employeeDto.getName());
		return employeeRepository.save(employee);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Employee updateEmployee(EmployeeDto employeeDto, Long id)
			throws InvalidUserException, DuplicatedInstanceException {
		Employee employee = employeeRepository.findOne(id);
		validateEmployee(employeeDto);
		userService.updateUser(employee, employeeDto.getEmail(), employeeDto.getRoles());
		employee.setName(employeeDto.getName());
		return employeeRepository.save(employee);

	}

	private void validateEmployee(EmployeeDto employeeDto) throws InvalidUserException {
		if (employeeDto == null) {
			throw new InvalidUserException("Employee is null");
		}
		if (employeeDto.getName() == null) {
			throw new InvalidUserException("Name is null");
		}
		if (employeeDto.getName().trim().length() == 0) {
			throw new InvalidUserException("Name is empty");
		}

	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(Long id) {
		return employeeRepository.findOne(id);
	}

}
