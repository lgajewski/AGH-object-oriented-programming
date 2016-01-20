package pl.edu.agh.iet.to2.app;

import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;
import pl.edu.agh.iet.to2.projects.IProjectsModule;
import pl.edu.agh.iet.to2.projects.ProjectsModuleImpl;
import pl.edu.agh.iet.to2.teams.ITeamsModule;

public class AppModuleManager implements ModuleManager {

    private IEmployeesModule employeesModule;
    private IProjectsModule projectsModule;

    public AppModuleManager() {
        this.employeesModule = initEmployeesModule();
        this.projectsModule = initProjectsModule();
    }

    private IProjectsModule initProjectsModule() {
        return new ProjectsModuleImpl();
    }

    private EmployeesModule initEmployeesModule() {
        EmployeeDao employeeDao = new EmployeeDao();
        return new EmployeesModule(employeeDao);
    }

    @Override
    public IEmployeesModule getEmployeesModule() {
        return employeesModule;
    }

    @Override
    public IProjectsModule getProjectsModule() {
        return projectsModule;
    }

    @Override
    public ITeamsModule getTeamsModule() {
        return null;
    }
}
