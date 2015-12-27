package pl.edu.agh.iet.to2;

import pl.edu.agh.iet.to2.employees.view.EmployeeTabInitializer;
import pl.edu.agh.iet.to2.projects.view.ProjectsTabInitializer;
import teams.view.TeamsTabInitializer;

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