package pl.edu.agh.iet.to2.employees;

import pl.edu.agh.iet.to2.employees.model.IEmployee;

import java.util.List;

public interface EmployeesModule {

    List<IEmployee> getEmployees();

}