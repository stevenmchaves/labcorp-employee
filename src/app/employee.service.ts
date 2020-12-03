import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Employee } from './employee';


@Injectable({ providedIn: 'root' })
export class EmployeeService {

  private url = 'http://localhost:8080/employee';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {}

    /** GET Employees from the server */
    getEmployees(): Observable<Employee[]> {
      return this.http.get<Employee[]>(this.url)
    }

/** GET employee by id. Return `undefined` when id not found */
getEmployeeNo404<Data>(id: number): Observable < Employee > {
  const url = `${this.url}/?id=${id}`;
  return this.http.get<Employee[]>(url)
    .pipe(
      map(employees => employees[0]), // returns a {0|1} element array
      tap(h => {
        const outcome = h ? `fetched` : `did not find`;
        console.log(`${outcome} employee id=${id}`);
      }),
      catchError(this.handleError<Employee>(`getEmployee id=${id}`))
    );
}

/** GET employee by id. Will 404 if id not found */
getEmployee(id: number): Observable < Employee > {
  const url = `${this.url}/${id}`;
  return this.http.get<Employee>(url).pipe(
    tap(_ => console.log(`fetched employee id=${id}`)),
    catchError(this.handleError<Employee>(`getEmployee id=${id}`))
  );
}

/** PUT: update workdays the employee on the server */
  updateWorkdays(emp: Employee, workDays: number): Observable<Employee> {
    console.log(`Adding ${workDays} work days to employee: ${emp.emailid}`);
  return this.http.put(`${this.url}/${emp.id}/workdays`, { workDays }, this.httpOptions).pipe(
    tap(_ => console.log(`updated emp id=${emp.id}`)),
    catchError(this.handleError<any>('updateWorkdays'))
  );
  }
  
  /** PUT: update vacation the employee on the server */
  updateVacation(emp: Employee, vacationDays: number): Observable<Employee> {
  console.log(vacationDays)
  return this.http.put(`${this.url}/${emp.id}/vacation`, { vacationDays }, this.httpOptions).pipe(
    tap(_ => console.log(`updated emp id=${emp.id}`)),
    catchError(this.handleError<any>('updateVacation'))
  );
}

  /**
   * Handle Http operation that failed.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result ?: T) {
  return (error: any): Observable<T> => {
    console.error(error); // log to console
    console.log(`${operation} failed: ${error.message}`);
    return of(result as T);
  };
}
}
