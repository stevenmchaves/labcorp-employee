package com.stevenchaves.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.Objects;


@Data
@Entity(name = "Employee")
@Table(name = "Employee")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SalaryEmployee.class, name = "S"),
        @JsonSubTypes.Type(value = HourlyEmployee.class, name = "E"),
        @JsonSubTypes.Type(value = Manager.class, name = "M")
})

public abstract class Employee {

    static final int MAX_WORKDAYS = 260;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected Long id;
    private float maxVacationDaysAllowed = 0;
    private float vacationDaysTaken = 0;
    private int totalDaysWorked = 0;
    private float numVacationDaysAcc = 0;
    private float remainingVacation;
    private String emailid;
    private String firstname;
    private String lastname;

    public Employee(Long id, float maxVacationDaysAllowed, float vacationDaysTaken, int totalDaysWorked, float numVacationDaysAcc, float remainingVacation, String emailid, String firstname, String lastname, String type) {
        this.id = id;
        this.maxVacationDaysAllowed = maxVacationDaysAllowed;
        this.vacationDaysTaken = vacationDaysTaken;
        this.totalDaysWorked = totalDaysWorked;
        this.numVacationDaysAcc = numVacationDaysAcc;
        this.remainingVacation = remainingVacation;
        this.emailid = emailid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
    }

    public float getVacationDaysTaken() {
        return this.vacationDaysTaken;
    }

    public void setVacationDaysTaken(float vacationDaysTaken) {
        this.vacationDaysTaken = vacationDaysTaken;
    }

    public float getRemainingVacation() {
        return this.remainingVacation;
    }

    public void setRemainingVacation(float remainingVacation) {
        this.remainingVacation = remainingVacation;
    }

    public Employee id(Long id) {
        this.id = id;
        return this;
    }

    public Employee maxVacationDaysAllowed(float maxVacationDaysAllowed) {
        this.maxVacationDaysAllowed = maxVacationDaysAllowed;
        return this;
    }

    public Employee vacationDaysTaken(float vacationDaysTaken) {
        this.vacationDaysTaken = vacationDaysTaken;
        return this;
    }

    public Employee totalDaysWorked(int totalDaysWorked) {
        this.totalDaysWorked = totalDaysWorked;
        return this;
    }

    public Employee numVacationDaysAcc(float numVacationDaysAcc) {
        this.numVacationDaysAcc = numVacationDaysAcc;
        return this;
    }

    public Employee remainingVacation(float remainingVacation) {
        this.remainingVacation = remainingVacation;
        return this;
    }

    public Employee emailid(String emailid) {
        this.emailid = emailid;
        return this;
    }

    public Employee firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public Employee lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Employee type(String type) {
        this.type = type;
        return this;
    }

    private String type;

    /**
     * @param maxVacationDaysAllowed
     */
    public Employee(int maxVacationDaysAllowed) {
        super();
        this.maxVacationDaysAllowed = maxVacationDaysAllowed;
    }

    /**
     * @param emailid
     * @param firstname
     * @param lastname
     * @param maxVacationDaysAllowed
     * @param type
     */
    public Employee(String emailid, String firstname, String lastname, int maxVacationDaysAllowed, String type) {
        super();
        this.maxVacationDaysAllowed = maxVacationDaysAllowed;
        this.emailid = emailid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
    }

    public Employee() {
        // default constructor
    }

    public float getMaxVacationDaysAllowed() {
        return maxVacationDaysAllowed;
    }

    public void setMaxVacationDaysAllowed(float maxVacationDaysAllowed) {
        this.maxVacationDaysAllowed = maxVacationDaysAllowed;
    }

    public float getMaxVacationDaysTaken() {
        return vacationDaysTaken;
    }

    public void setMaxVacationDaysTaken(float maxVacationDaysTaken) {
        this.vacationDaysTaken = maxVacationDaysTaken;
    }

    public int getTotalDaysWorked() {
        return totalDaysWorked;
    }

    public void setTotalDaysWorked(int totalDaysWorked) {
        this.totalDaysWorked = totalDaysWorked;
    }

    public float getNumVacationDaysAcc() {
        return numVacationDaysAcc;
    }

    public void setNumVacationDaysAcc(float numVacationDaysAcc) {
        this.numVacationDaysAcc = numVacationDaysAcc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /**
     * @return the emailid
     */
    public String getEmailid() {
        return emailid;
    }

    /**
     * @param emailid the emailid to set
     */
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Float.compare(employee.getMaxVacationDaysAllowed(), getMaxVacationDaysAllowed()) == 0 &&
                Float.compare(employee.getMaxVacationDaysTaken(), getMaxVacationDaysTaken()) == 0 &&
                getTotalDaysWorked() == employee.getTotalDaysWorked() &&
                Float.compare(employee.getNumVacationDaysAcc(), getNumVacationDaysAcc()) == 0 &&
                getId().equals(employee.getId()) &&
                getEmailid().equals(employee.getEmailid()) &&
                getFirstname().equals(employee.getFirstname()) &&
                getLastname().equals(employee.getLastname()) &&
                getType().equals(employee.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMaxVacationDaysAllowed(), getMaxVacationDaysTaken(), getTotalDaysWorked(),
                getNumVacationDaysAcc(), getEmailid(), getFirstname(), getLastname(), getType());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", maxVacationDaysAllowed=" + maxVacationDaysAllowed +
                ", maxVacationDaysTaken=" + vacationDaysTaken +
                ", totalDaysWorked=" + totalDaysWorked +
                ", numVacationDaysAcc=" + numVacationDaysAcc +
                ", emailid='" + emailid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }


    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void Work(Integer workdays) throws Exception {
        System.out.println(workdays);
        if (workdays >= 0 && (this.totalDaysWorked + workdays) <= MAX_WORKDAYS) {
            if (this.numVacationDaysAcc == this.maxVacationDaysAllowed) {
                System.out.println("All vacation days have been accumulated");
            } else {
                System.out.println(String.format("Workdays: %d, Max-Workdays: %d Allowed: %f", workdays, MAX_WORKDAYS, this.maxVacationDaysAllowed));
                this.totalDaysWorked += workdays;
                float percentWorkDays = this.totalDaysWorked / (float) MAX_WORKDAYS;
                this.numVacationDaysAcc = percentWorkDays * this.maxVacationDaysAllowed;
            }
        } else {
            throw new Exception(workdays + " is invalid!");
        }
    }

    public void TakeVacation(Float vacationDaysUsed) throws Exception {
        boolean foundIssue = false;
        String errorMsg = "";

        // cannot exceed Allowed
        if (vacationDaysUsed >= 0 && vacationDaysUsed <= maxVacationDaysAllowed) {
            if (vacationDaysUsed <= numVacationDaysAcc) {
                if (vacationDaysUsed > (this.maxVacationDaysAllowed - this.vacationDaysTaken)) {
                    foundIssue = true;
                    errorMsg = String.format("%f is over the remaining days available for the employer", vacationDaysUsed);
                } else {
                    this.vacationDaysTaken += vacationDaysUsed;
                    this.remainingVacation = this.maxVacationDaysAllowed - this.vacationDaysTaken;
                }
            } else {
                foundIssue = true;
                errorMsg = String.format("%f is over the accumulated vacation days available for the employee", vacationDaysUsed);
            }
        } else {
            foundIssue = true;
            errorMsg = "Employee is not allowed to take: " + vacationDaysUsed;
        }

        if (foundIssue) {
            throw new Exception(errorMsg);
        }
    }

}
