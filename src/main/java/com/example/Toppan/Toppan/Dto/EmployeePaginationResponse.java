package com.example.Toppan.Toppan.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePaginationResponse<T> {
	 
	int recordCount;
	T response;

}
