package es.mabu.mr.user.employee;

import java.util.List;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.user.exception.InvalidUserException;

public interface EmployeeService {

	Employee createEmployee(EmployeeDto employee) throws InvalidUserException, DuplicatedInstanceException;

	List<Employee> getEmployees();

	Employee findById(Long id);

	Employee updateEmployee(EmployeeDto employeeDto, Long id) throws InvalidUserException, DuplicatedInstanceException;

}
