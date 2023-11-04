package net.javaguides.springboot.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.EmployeeServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private final EmployeeServiceImpl employeeServiceImpl;

	public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl = employeeServiceImpl;
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return ResponseEntity.ok(employeeServiceImpl.findAllEmployees());
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		return ResponseEntity.ok(employeeServiceImpl.createEmployee(employee));
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id){
		return ResponseEntity.ok(employeeServiceImpl.findEmployeeById(id));
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Optional<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
		//return ResponseEntity.ok(employeeServiceImpl.updateEmployee(employee));
			return ResponseEntity.ok(employeeServiceImpl.updateEmployee(id, employee));
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		return ResponseEntity.ok(employeeServiceImpl.deleteEmployee(id));
	}
	
}
