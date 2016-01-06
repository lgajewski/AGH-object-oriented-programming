package pl.edu.agh.iet.to2.employees.module;

import pl.edu.agh.iet.to2.ModuleManager;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;

import java.util.List;

public class EmployeeUpdater {

    public static void update() {
        EmployeesModuleImpl employeesModule = (EmployeesModuleImpl) ModuleManager.getEmployeesModule();

        // clear all saved employees
        employeesModule.clearAll();

        List<Employee> employeeList = EmployeeDao.getEmployees();
        employeeList.forEach(employeesModule::addEmployee);
    }

}
