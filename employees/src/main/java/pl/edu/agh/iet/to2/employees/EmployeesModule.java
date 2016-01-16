package pl.edu.agh.iet.to2.employees;


import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesModule implements IEmployeesModule {

    private EmployeeDao employeeDao;

    public EmployeesModule(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public IEmployee getEmployeeId(long id) {
        return employeeDao.getEmployeeId(id);
    }

    @Override
    public List<IEmployee> getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        return new ArrayList<>(employees);
    }

    @Override
    public List<IEmployee> getFilteredEmployees(Predicate predicate) {
        List<IEmployee> employees = getEmployees();

        // use filter on each employee and collect results into the list
        return employees.stream().filter(predicate::filter).collect(Collectors.toList());
    }
}