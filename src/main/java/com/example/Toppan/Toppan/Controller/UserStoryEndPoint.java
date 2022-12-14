package com.example.Toppan.Toppan.Controller;

import org.springframework.http.MediaType;

import java.awt.print.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Toppan.Toppan.Dto.EmployeeDTO;
import com.example.Toppan.Toppan.Dto.EmployeePaginationResponse;
import com.example.Toppan.Toppan.Model.Employee;
import com.example.Toppan.Toppan.Service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class UserStoryEndPoint {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = "/users/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public ResponseEntity<Void> postEmployeeData(@RequestParam("file") MultipartFile file) {
		log.info("Start postEmployeeData(), file={}", file);
		if (!file.isEmpty()) {
			employeeService.upload(file);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/usersPagination/")
	public EmployeePaginationResponse<List<Employee>> getAllEmployees(@RequestBody String field) {
		List<Employee> list = employeeService.getAllEmployees(field);
		return new EmployeePaginationResponse<>(list.size(), list);
	}

	@GetMapping("/users/")
	public EmployeePaginationResponse<List<Employee>> getAllEmployeesWithPagination(@RequestBody int minSalary, int maxSalary,
			int offset, int limit, String field) {
		List<Employee> emp = employeeService.getAllEmployeesWithPagination(minSalary, maxSalary, offset, limit, field);
		return new EmployeePaginationResponse<>(emp.size(), emp);
	}

	@GetMapping("/users/getAll")
	public ResponseEntity<List<Employee>> getAllEmployees() {

		return new ResponseEntity<>(employeeService.getEmployee(), HttpStatus.OK);
	}

	@PostMapping(path = "/users/add",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		log.info("Received details = {}", employee);
		return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
	}

	@PutMapping(path = "/users/update",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		log.info("Received details = {}", employee);
		return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}

	@DeleteMapping("/users/delete/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
		
		employeeService.deletEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
