package com.example.Toppan.Toppan.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Toppan.Toppan.Model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

	Optional<Employee> findByIdAndLogin(String id, String login);



	List<Employee> findAll(Sort by);
	
	Page<Employee> findAll(PageRequest page);
	
	List<Employee> findAll();

}
