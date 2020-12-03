package com.stevenchaves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class EmployeeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
}