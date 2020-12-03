package com.stevenchaves.employee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevenchaves.employee.exceptions.ApiError;
import com.stevenchaves.employee.exceptions.ItemNotFoundException;
import com.stevenchaves.employee.repo.EmployeeRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final Logger log = Logger.getLogger(EmployeeService.class);

    private final EmployeeRepo empRepo;

    public EmployeeService(EmployeeRepo EmployeeRepo) {
        this.empRepo = EmployeeRepo;
    }

    public Iterable<Employee> list() {
        return empRepo.findAll();
    }

    public Employee get(Long id) {
        Optional<Employee> Employee = empRepo.findById(id);
        if (Employee.isEmpty()) {
            throw new ItemNotFoundException(id);
        }
        return Employee.get();
    }

    public List<Employee> saveEmployee(List<Employee> EmployeeList) {
        List<Employee> createdEmployeeList = new ArrayList<>();
        for (long index = 1; index <= EmployeeList.size(); index++) {
            Employee tempEmployee = EmployeeList.get((int) (index - 1));
            createdEmployeeList.add(empRepo.save(tempEmployee));

        }
        return createdEmployeeList;
    }

    public void addEmployee(Employee employee) {
        this.empRepo.save(employee);
    }

    public void reset() {
        empRepo.deleteAll();
        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Employee>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("available_employee.json");
        try {
            List<Employee> employeeList = mapper.readValue(Objects.requireNonNull(inputStream), typeReference);
            saveEmployee(employeeList);
            log.debug("Employee List Saved!");

        } catch (IOException e) {
            log.error("Unable to save Employee List: ", e);
        }
    }


    public void delete(Long id) {
        Optional<Employee> Employee = empRepo.findById(id);
        if (Employee.isEmpty()) {
            throw new ItemNotFoundException(id);
        }
        empRepo.deleteById(id);

    }

    /**
     * Updates existing Employee Object.
     *
     * @param id   which id to update
     * @param data body
     * @return success or failure response entity
     */
    public ResponseEntity<Employee> update(Long id, Employee data) {
        Optional<Employee> EmployeeOptional = empRepo.findById(id);
        if (EmployeeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        empRepo.save(data);
        return ResponseEntity.ok(data);
    }

    public void deleteAll() {
        empRepo.deleteAll();
        empRepo.flush();
        System.out.println("All Employee items have been deleted.");
        System.out.println("Number of items: " + empRepo.count());
    }

    public List<Employee> getEmployee(String string) {
        return null;
    }

    public HttpEntity<? extends Object> updateVacation(String id, Days vacationDays) {
        Long idLong = Long.valueOf(id);
        Optional<Employee> emp = empRepo.findById(idLong);
        if (emp.isEmpty()) {
            throw new ItemNotFoundException(idLong);
        }
        Employee employee = emp.get();
        if (employee == null) {
            throw new ItemNotFoundException("Employee", id);
        }
        try {
            employee.TakeVacation(vacationDays.vacationDays);
        } catch (Exception e) {
            return this.handleInvalid(e);
        }
        return this.update(idLong, employee);
    }

    public HttpEntity<? extends Object> updateWorkDays(String id, Days workDays) {
        Long idLong = Long.valueOf(id);
        Optional<Employee> emp = empRepo.findById(idLong);
        if (emp.isEmpty()) {
            throw new ItemNotFoundException(idLong);
        }
        Employee employee = emp.get();
        if (employee == null) {
            throw new ItemNotFoundException("Employee", id);
        }
        System.out.println(workDays);
        try {
            employee.Work(workDays.workDays);
        } catch (Exception e) {
            return this.handleInvalid(e);
        }
        return this.update(idLong, employee);
    }

    protected ResponseEntity<Object> handleInvalid(Exception ex) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
