package pl.edu.agh.iet.to2.app;


import pl.edu.agh.iet.to2.employees.EmployeeTabInitializer;
import pl.edu.agh.iet.to2.projects.ProjectsTabInitializer;
import pl.edu.agh.iet.to2.teams.TeamsTabInitializer;

enum AppTab {
    EMPLOYEES(new EmployeeTabInitializer()),
    PROJECTS(new ProjectsTabInitializer()),
    TEAMS(new TeamsTabInitializer());

    private final TabInitializer tab;

    AppTab(TabInitializer tab) {
        this.tab = tab;
    }

    public TabInitializer getTabInitializer() {
        return tab;
    }

    public String getName() {
        return toString();
    }
}