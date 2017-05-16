package es.mabu.mr.user.employee;

import org.springframework.stereotype.Repository;

import es.mabu.mr.user.user.UserBaseRepository;

/**
 * The AccountRepository interface is a Spring Data JPA data repository for
 * Account entities. The AccountRepository provides all the data access
 * behaviors exposed by <code>JpaRepository</code> and additional custom
 * behaviors may be defined in this interface.
 */
@Repository
public interface EmployeeRepository extends UserBaseRepository<Employee> {

}