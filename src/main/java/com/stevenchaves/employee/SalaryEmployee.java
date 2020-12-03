package com.stevenchaves.employee;

import javax.persistence.Entity;

@Entity
public class SalaryEmployee extends Employee {

	/** Normal Salary Employee Constructor */
	public SalaryEmployee() {
		super(15);
	}

	/** Manager Employee Constructor */
	public SalaryEmployee(int vacationDays) {
		super(vacationDays);
	}
}
