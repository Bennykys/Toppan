package com.example.Toppan.Toppan.Controller;

import org.springframework.http.MediaType;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Toppan.Toppan.Service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class UserStoryEndPoint {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = "/users/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public ResponseEntity<Void> postEmployeeData(@RequestParam("file") MultipartFile file) {
		log.info("Start postEmployeeData(), file={}", file);
		if (!file.isEmpty()) {
			employeeService.upload(file);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
