package com.example.Toppan.Toppan.Service;


import java.awt.print.Pageable;

import org.springframework.web.multipart.MultipartFile;

import com.example.Toppan.Toppan.Dto.EmployeePaginationResponse;

public interface EmployeeService {
	
	void upload(MultipartFile mFile);
	
	public EmployeePaginationResponse readEmployee(Pageable pageable) ;

}
