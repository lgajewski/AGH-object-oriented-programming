package pl.edu.agh.iet.to2;

import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.employees.persistence.EmployeesModuleImpl;

public class ModuleManager {

    private static EmployeesModule employeesModule;

    public static EmployeesModule getEmployeesModule() {
        if (employeesModule == null) {
            employeesModule = new EmployeesModuleImpl();
        }

        return employeesModule;
    }

}
