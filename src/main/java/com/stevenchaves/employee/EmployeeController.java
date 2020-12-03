package com.stevenchaves.employee;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Uses to determine how to handle requests for Employee data type
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employee")
	public Iterable<Employee> list() {
		return this.employeeService.list();
	}

	@GetMapping("/employee/{id}")
	public Employee get(@PathVariable Long id) {
		return this.employeeService.get(id);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?>  delete(@PathVariable String id) {
		this.employeeService.delete(Long.decode(id));
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/employee/reset")
	public void reset() {
		this.employeeService.reset();
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee data) {
		return this.employeeService.update(Long.getLong(id), data);
	}

	@PutMapping("/employee/{id}/vacation")
	public HttpEntity<?> updateEmployeeVacation(@PathVariable String id, @RequestBody Days data) {
		System.out.println(data);
		return this.employeeService.updateVacation(id, data);
	}

	@PutMapping("/employee/{id}/workdays")
	public HttpEntity<?> updateEmployeeWork(@PathVariable String id, @RequestBody Days data) {
		System.out.println(data);
		return this.employeeService.updateWorkDays(id, data);
	}

	// Not Needed/Used for Assignment
	@PostMapping("/employee")
	public void createEmployee(@RequestBody Employee data) {
		this.employeeService.addEmployee(data);
	}
	
}
