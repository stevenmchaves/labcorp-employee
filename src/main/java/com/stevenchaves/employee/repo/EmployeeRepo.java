package com.stevenchaves.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.stevenchaves.employee.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findById(Long id);
}