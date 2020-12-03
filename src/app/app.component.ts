import { Component, OnInit } from '@angular/core';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Welcome to Employee Work and Vacation Tracker!';
  employees: Employee[] = [];
  selectedEmp: Employee;

  constructor(private empService: EmployeeService) { }

  ngOnInit() {
    this.getEmployees();
  }

  getEmployees(): void {
    this.empService.getEmployees()
      .subscribe(employees => this.employees = employees);
  }


  onSelect(emp: any): void {
    this.selectedEmp = this.employees.find(employee => employee.emailid === emp.target.value);
  }

  updateWorkDays(workDays: number): void {;
    this.empService.updateWorkdays(this.selectedEmp, workDays).subscribe(employee => {
      if (employee) {
        this.selectedEmp = employee
        console.log('Employee Information: ' + this.selectedEmp.toString());
      } else {
        console.error('Update Work Days failed.');
      }
    });
  }

  updateVacation(vacationDays: number): void {
    this.empService.updateVacation(this.selectedEmp, vacationDays).subscribe(employee => {
      if (employee) {
        this.selectedEmp = employee
        console.log('Employee Information: ' + this.selectedEmp.toString());
      } else {
        console.error('Update Vacation failed.');
      }
    }
    );
  }
}
