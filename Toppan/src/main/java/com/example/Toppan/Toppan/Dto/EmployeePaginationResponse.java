package com.example.Toppan.Toppan.Dto;

import java.util.List;

import com.example.Toppan.Toppan.Model.Employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeePaginationResponse {
	  private List<Employee> employees;
	  private Long numberOfItems;
	  private int numberOfPages;

}
