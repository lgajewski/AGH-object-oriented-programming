package pl.edu.agh.iet.to2;

import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.employees.persistence.EmployeesModuleImpl;
import pl.edu.agh.iet.to2.projects.ProjectsModule;
import pl.edu.agh.iet.to2.projects.ProjectsModuleImpl;
import pl.edu.agh.iet.to2.teams.TeamsModule;
import pl.edu.agh.iet.to2.teams.dummy.DummyTeamsModule;

public class ModuleManager {

    private static EmployeesModule employeesModule;
    private static ProjectsModule projectsModule;
    private static TeamsModule teamsModule;

    public static EmployeesModule getEmployeesModule() {
        if (employeesModule == null) {
            employeesModule = new EmployeesModuleImpl();
        }

        return employeesModule;
    }

    public static ProjectsModule getProjectsModule() {
        if (projectsModule == null) {
            projectsModule = new ProjectsModuleImpl();
        }
        return projectsModule;
    }

    public static TeamsModule getTeamsModule() {
        if (teamsModule == null) {
            teamsModule = new DummyTeamsModule();
        }
        return teamsModule;
    }

}
