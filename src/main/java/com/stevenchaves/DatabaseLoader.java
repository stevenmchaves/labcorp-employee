package com.stevenchaves;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevenchaves.employee.Employee;
import com.stevenchaves.employee.EmployeeService;

@Component
public class DatabaseLoader {

	private final Logger log = Logger.getLogger(DatabaseLoader.class);

	@Bean
	CommandLineRunner EmployeeRunner(EmployeeService employeeService) {
		return args -> {

			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Employee>> typeReference = new TypeReference<>() {
			};
			InputStream inputStream = ClassLoader.getSystemResourceAsStream("available_employee.json");
			try {
				List<Employee> empList = mapper.readValue(Objects.requireNonNull(inputStream), typeReference);
				employeeService.saveEmployee(empList);
				log.debug("Employee List Saved!");
				employeeService.list().forEach(log::info);

			} catch (IOException e) {
				log.error("Unable to save Employee List: ", e);
			}
		};
	}
}
