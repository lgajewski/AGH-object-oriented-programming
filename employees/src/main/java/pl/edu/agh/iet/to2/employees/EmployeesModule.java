package pl.edu.agh.iet.to2.employees;


import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;
import pl.edu.agh.iet.to2.employees.persistence.EventNotifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesModule implements IEmployeesModule {

    private EmployeeDao employeeDao;
    private EventNotifier eventNotifier;

    public EmployeesModule(EmployeeDao employeeDao, EventNotifier eventNotifier) {
        this.employeeDao = employeeDao;
        this.eventNotifier = eventNotifier;
    }

    @Override
    public IEmployee getEmployeeId(long id) {
        return employeeDao.getEmployeeById(id);
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

    @Override
    public void addOnEmployeeUpdatedListener(OnEmployeeUpdateListener listener) {
        eventNotifier.addOnEmployeeUpdatedListener(listener);
    }

    @Override
    public void addOnEmployeeAddedListener(OnEmployeeEventListener listener) {
        eventNotifier.addOnEmployeeAddedListener(listener);
    }

    @Override
    public void addOnEmployeeDeletedListener(OnEmployeeEventListener listener) {
        eventNotifier.addOnEmployeeDeletedListener(listener);
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

}