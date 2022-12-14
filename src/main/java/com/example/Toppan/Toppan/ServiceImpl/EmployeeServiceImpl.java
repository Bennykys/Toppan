package com.example.Toppan.Toppan.ServiceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Toppan.Toppan.Dto.EmployeeDTO;
import com.example.Toppan.Toppan.Exception.CustomException;
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

	private String filepath = "C:/Users/65901/Desktop/LAPTOP/Toppan/";

	@Override
	public void upload(MultipartFile mFile) {

		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filepath + mFile.getOriginalFilename()));
			while ((line = br.readLine()) != null) {

				String[] data = line.split(",");

				Optional<Employee> Employee = employeeRepository.findByIdAndLogin(data[0], data[1]);
				if (!Employee.isPresent() && Double.valueOf(data[3]) > 0 && !data[0].contains("#")) {
					Employee employee = new Employee();
					employee.setId(data[0]);
					employee.setLogin(data[1]);
					employee.setName(data[2]);
					employee.setSalary(Double.valueOf(data[3]));
					employeeRepository.save(employee);
				} else if (Employee.isPresent() && Double.valueOf(data[3]) > 0 && !data[0].contains("#")) {
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
	public List<Employee> getAllEmployees(String field) {

		List<Employee> allEmployee = field.startsWith("+")
				? employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field))
				: field.startsWith("-") ? employeeRepository.findAll(Sort.by(Sort.Direction.DESC, field))
						: employeeRepository.findAll();

		return allEmployee;

	}

	@Override
	public List<Employee> getAllEmployeesWithPagination(int minSalary, int maxSalary, int offset, int pageSize,
			String field) {

		List<Employee> empBeforeSort = field.startsWith("+")
				? employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field))
				: field.startsWith("-") ? employeeRepository.findAll(Sort.by(Sort.Direction.DESC, field))
						: employeeRepository.findAll();

		List<Employee> employee = empBeforeSort.stream()
				.filter(e -> e.getSalary() >= minSalary && e.getSalary() <= maxSalary).skip(offset).limit(pageSize)
				.collect(Collectors.toList());

		return employee;

	}

	@Override
	public List<Employee> getEmployee() {
		List<Employee> allEmployee = employeeRepository.findAll();

		return allEmployee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		
		Optional<Employee> emp = employeeRepository.findById(employee.getId());
		if (!emp.isPresent()) {
			throw new CustomException("User does not exist");
		}
		Employee existEmp = emp.get();
		existEmp.setId(employee.getId());
		existEmp.setLogin(employee.getLogin());
		existEmp.setName(employee.getName());
		existEmp.setSalary(employee.getSalary());
		employeeRepository.save(existEmp);
		return existEmp;
	}

	@Override
	public void deletEmployee(String id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (!emp.isPresent()) {
			throw new CustomException("User does not exist");
		}
		Employee existEmp = emp.get();
		employeeRepository.delete(existEmp);

	}

	@Override
	public Employee addEmployee(Employee employee) {
		log.info("emp = {}", employee);
		Optional<Employee> emp = employeeRepository.findById(employee.getId());
		if (emp.isPresent()) {
			throw new CustomException("User already exist");
		}
		Employee newEmployee = new Employee();
		newEmployee.setId(employee.getId());
		newEmployee.setLogin(employee.getLogin());
		newEmployee.setName(employee.getName());
		newEmployee.setSalary(employee.getSalary());
		employeeRepository.save(newEmployee);
		return newEmployee;
	}

}
