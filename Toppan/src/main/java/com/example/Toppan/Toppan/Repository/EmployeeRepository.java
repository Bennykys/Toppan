package com.example.Toppan.Toppan.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Toppan.Toppan.Model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

	Optional<Employee> findByIdAndLogin(String id, String login);
}
