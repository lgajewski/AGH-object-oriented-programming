package pl.edu.agh.iet.to2.projects.dummy;

import pl.edu.agh.iet.to2.projects.model.Employee;
import pl.edu.agh.iet.to2.projects.model.Salary;

public interface IEmployeesSource {
    Salary getEmployeeSalary(Employee employee);
}
