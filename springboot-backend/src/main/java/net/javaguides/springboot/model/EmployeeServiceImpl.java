package net.javaguides.springboot.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
		/*	Optional<Employee> employee = employeeServiceImpl.findEmployeeById(id);
		if (employee.isPresent()){
			return ResponseEntity.ok(employee);
		} else {
			throw new ResourceNotFoundException("Employee not exist with id: " + id);
		}*/
        return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not exist with id: " + id)));
    }

    @Override
    public Optional<Employee> updateEmployee(Long id, Employee employee) {
        //Éste método funciona con findById(id) y también con findEmployeeById(id)
        Employee updateEmployee = findEmployeeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setEmailId(employee.getEmailId());
        return Optional.of(employeeRepository.save(updateEmployee));
    }

    @Override
    public Map<String, Boolean> deleteEmployee(Long id) {
        Employee deleteEmployee = findEmployeeById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
		employeeRepository.delete(deleteEmployee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("User deleted", Boolean.TRUE);
        return response;
    }

}
