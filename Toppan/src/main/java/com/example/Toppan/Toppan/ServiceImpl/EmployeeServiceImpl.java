package com.example.Toppan.Toppan.ServiceImpl;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Toppan.Toppan.Dto.EmployeePaginationResponse;
import com.example.Toppan.Toppan.Model.Employee;
import com.example.Toppan.Toppan.Repository.EmployeeRepository;
import com.example.Toppan.Toppan.Service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	private String line = "";

	private String filepath = "/Users/65901/Desktop/Toppan/";

	@Override
	public void upload(MultipartFile mFile) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath + mFile.getOriginalFilename()));
			while ((line = br.readLine()) != null) {

				String[] data = line.split(",");

				Optional<Employee> Employee = employeeRepository.findByIdAndLogin(data[0], data[1]);
				if (!Employee.isPresent() && Double.valueOf(data[3]) > 0 && data[0].contains("#")) {
					Employee employee = new Employee();
					employee.setId(data[0]);
					employee.setLogin(data[1]);
					employee.setName(data[2]);
					employee.setSalary(Double.valueOf(data[3]));
					employeeRepository.save(employee);
				} else if (Employee.isPresent() && Double.valueOf(data[3]) > 0 && data[0].contains("#")) {
					Employee.get().setLogin(data[1]);
					Employee.get().setName(data[2]);
					Employee.get().setSalary(Double.valueOf(data[3]));
					employeeRepository.save(Employee.get());
				} else {
					log.error("Salary cannot be lower than 0");
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public EmployeePaginationResponse readEmployee(Pageable pageable) {

		Page<Employee> employee = employeeRepository.findAll(pageable);

		return EmployeePaginationResponse.builder().numberOfItems(employee.getTotalElements())
				.numberOfPages(employee.getTotalPages()).employees(employee.getContent()).build();
	}
}
