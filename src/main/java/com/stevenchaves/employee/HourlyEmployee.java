package com.stevenchaves.employee;

import javax.persistence.Entity;

@Entity
public class HourlyEmployee extends Employee{
    /** Normal Hourly Employee Constructor */
    public HourlyEmployee() {
        super(10);
    }
}
