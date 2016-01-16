package pl.edu.agh.iet.to2.app;

import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.projects.IProjectsModule;
import pl.edu.agh.iet.to2.teams.ITeamsModule;

public interface ModuleManager {

    IEmployeesModule getEmployeesModule();

    IProjectsModule getProjectsModule();

    ITeamsModule getTeamsModule();

}
