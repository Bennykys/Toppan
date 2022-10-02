package com.example.Toppan.Toppan.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "EMPLOYEE")
@NamedQuery(name = "Employee.findAll", query = "SELECT a FROM Employee a")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;
	
	@NonNull
	@Column(name = "LOGIN")
	private String login;
	
	@NonNull
	@Column(name = "NAME")
	private String name;
	
	@NonNull
	@Column(name = "SALARY")
	private Double salary;

}