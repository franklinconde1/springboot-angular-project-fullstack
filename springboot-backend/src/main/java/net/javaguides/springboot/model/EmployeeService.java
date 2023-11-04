package net.javaguides.springboot.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

	List<Employee> findAllEmployees();

	Employee createEmployee(Employee employee);
	Optional<Employee> findEmployeeById(Long id);

	//Una forma de hacer el update
	//Optional<Employee> updateEmployee(Employee employee);

	//Otra forma de hacer el update
	Optional<Employee> updateEmployee(Long id, Employee employee);

	Map<String, Boolean> deleteEmployee(Long id);

}
