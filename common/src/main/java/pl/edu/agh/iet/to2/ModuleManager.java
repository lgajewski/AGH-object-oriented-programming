package pl.edu.agh.iet.to2;

import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.employees.persistence.EmployeesModuleImpl;
import pl.edu.agh.iet.to2.projects.ProjectsModule;
import pl.edu.agh.iet.to2.projects.ProjectsModuleImpl;

public class ModuleManager {

    private static EmployeesModule employeesModule;

    private static ProjectsModule projectsModule = new ProjectsModuleImpl();

    public static EmployeesModule getEmployeesModule() {
        if (employeesModule == null) {
            employeesModule = new EmployeesModuleImpl();
        }

        return employeesModule;
    }

    public static ProjectsModule getProjectsModule() {
        return projectsModule;
    }

}
