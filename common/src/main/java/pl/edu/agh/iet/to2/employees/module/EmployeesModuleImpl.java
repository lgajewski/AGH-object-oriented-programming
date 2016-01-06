package pl.edu.agh.iet.to2.employees.module;


import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.employees.IEmployee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeesModuleImpl implements EmployeesModule {

    private Map<Long, IEmployee> employeeMap;

    public EmployeesModuleImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public IEmployee getEmployeeId(long id) {
        return employeeMap.get(id);
    }

    @Override
    public List<IEmployee> getEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public List<IEmployee> getFilteredEmployees(Predicate predicate) {
        List<IEmployee> filteredEmployees = new ArrayList<>();
        List<IEmployee> allEmployees = getEmployees();

        filteredEmployees.addAll(allEmployees.stream().collect(Collectors.toList()));

        return filteredEmployees;
    }

    protected void clearAll() {
        employeeMap.clear();
    }

    protected void addEmployee(IEmployee employee) {
        employeeMap.put(employee.getId(), employee);
    }

}