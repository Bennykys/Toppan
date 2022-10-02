package com.example.Toppan.Toppan.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
	
	@NonNull
	private String id;
	
	@NonNull
	private String login;
	
	@NonNull
	private String name;
	
	@NonNull
	private Double salary;

}
