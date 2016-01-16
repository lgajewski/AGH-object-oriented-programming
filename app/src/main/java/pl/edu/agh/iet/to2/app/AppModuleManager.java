package pl.edu.agh.iet.to2.app;

import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;
import pl.edu.agh.iet.to2.projects.IProjectsModule;
import pl.edu.agh.iet.to2.teams.ITeamsModule;

public class AppModuleManager implements ModuleManager {
    @Override
    public IEmployeesModule getEmployeesModule() {
        EmployeeDao employeeDao = new EmployeeDao();
        return new EmployeesModule(employeeDao);
    }

    @Override
    public IProjectsModule getProjectsModule() {
        return null;
    }

    @Override
    public ITeamsModule getTeamsModule() {
        return null;
    }
}
