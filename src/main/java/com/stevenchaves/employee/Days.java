package com.stevenchaves.employee;

import javax.persistence.*;

@Entity
public class Days  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;
	protected Integer workDays;
	protected float vacationDays;

	public Days(Integer workDays, float vacationDays) {
		this.workDays = workDays;
		this.vacationDays = vacationDays;
	}

	/**
	 * @param workDays
	 */
	public Days(String workDays) {
		super();
		this.workDays = Integer.valueOf(workDays);
	}

	@Override
	public String toString() {
		return "Days{" +
				"workDays=" + workDays +
				", vacationDays=" + vacationDays +
				'}';
	}
}
