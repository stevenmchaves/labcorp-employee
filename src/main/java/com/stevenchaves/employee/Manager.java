package com.stevenchaves.employee;

import javax.persistence.Entity;

@Entity
public class Manager extends SalaryEmployee {
	public Manager() {
		super(30);
	}
}
