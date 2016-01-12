package pl.edu.agh.iet.to2.projects.dummy;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iet.to2.projects.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DummyProjects implements IProjectsSource {

    private ObservableList<Project> projects = FXCollections.observableArrayList();

    public DummyProjects(){
        Team team1 = new Team();
        team1.setName("team1");
        Team team2 = new Team();
        team2.setName("team2");
        TeamMember tm1 = new TeamMember(team1, new Employee(0, "Marek", "Marek", new Salary(new BigDecimal(3000)), "Junior Software Engineer"));
        TeamMember tm2 = new TeamMember(team1, new Employee(1, "Darek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm3 = new TeamMember(team1, new Employee(2, "Jarek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm4 = new TeamMember(team2, new Employee(3, "Jan", "Nowak", new Salary(new BigDecimal(6000)), "Senior Software Engineer"));
        team1.addTeamMember(tm1);
        team1.addTeamMember(tm2);
        team1.addTeamMember(tm3);
        team2.addTeamMember(tm4);
        Project p1 = new Project(1, "AirBerlin", LocalDate.of(2015, 8, 24), LocalDate.of(2015, 9, 14), BigDecimal.valueOf(27000L));
        p1.addTeam(team1);
        p1.addTeam(team2);
        p1.addRole(tm1, "Developer");
        p1.addRole(tm2, "Developer");
        p1.addRole(tm3, "Tech Lead");
        p1.addRole(tm4, "Tester");
        Project p2 = new Project(2, "Lufthansa", LocalDate.of(2016, 6, 9), LocalDate.of(2017, 1, 18), BigDecimal.valueOf(50000L));
        p2.addTeam(team1);
        p2.addTeam(team2);
        p2.addRole(tm1, "Developer");
        p2.addRole(tm2, "Developer");
        p2.addRole(tm4, "Tech Lead");
        p2.addRole(tm3, "Tester");
        Project p3 = new Project(3, "LOT", LocalDate.of(2015, 1, 12), LocalDate.of(2016, 3, 1), BigDecimal.valueOf(100000L));
        p3.addTeam(team1);
        p3.addTeam(team2);
        p3.addRole(tm2, "Developer");
        p3.addRole(tm1, "Developer");
        p3.addRole(tm3, "Tech Lead");
        p3.addRole(tm4, "Tester");
        Project p4 =new Project(4, "WizzAir", LocalDate.of(2015, 9, 9), LocalDate.of(2015, 12, 30), BigDecimal.valueOf(15000L));
        p4.addTeam(team1);
        p4.addTeam(team2);
        p4.addRole(tm2, "Developer");
        p4.addRole(tm4, "Developer");
        p4.addRole(tm3, "Tech Lead");
        p4.addRole(tm1, "Tester");
        projects.add(p1);
        projects.add(p2);
        projects.add(p3);
        projects.add(p4);
    }

    @Override
    public ObservableList<Project> getProjects() {
        return projects;
    }
}
