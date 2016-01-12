package pl.edu.agh.iet.to2.projects.dummy;

import othersmodel.Employee;
import othersmodel.Salary;

public interface IEmployeesSource {
    Salary getEmployeeSalary(Employee employee);
}
