package com.example.Toppan.Toppan.Service;


import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import com.example.Toppan.Toppan.Model.Employee;

public interface EmployeeService {
	
	void upload(MultipartFile mFile);
	
	List<Employee> getAllEmployees(String field);
	
	Page<Employee> getAllEmployeesWithPaginaation(int offset, int pageSize);

}
