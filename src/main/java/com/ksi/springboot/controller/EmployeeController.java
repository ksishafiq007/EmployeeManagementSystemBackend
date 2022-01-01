package com.ksi.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksi.springboot.exception.ResourceNotFoundException;
import com.ksi.springboot.model.Employee;
import com.ksi.springboot.repository.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")                         
	public List<Employee> getAllEmployees(){                          // Get all employees
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {              // Create Employee rest api
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exis with id :" + id));
		return ResponseEntity.ok(employee);
	}

}
