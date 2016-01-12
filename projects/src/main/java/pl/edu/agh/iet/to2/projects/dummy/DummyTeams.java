package pl.edu.agh.iet.to2.projects.dummy;

import interfaces.ITeam;
import interfaces.modules.ITeamsSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import othersmodel.Employee;
import othersmodel.Salary;
import othersmodel.Team;
import othersmodel.TeamMember;

import java.math.BigDecimal;

/**
 * Created by Marcin on 2015-12-16.
 */
public class DummyTeams implements ITeamsSource {

    ObservableList<ITeam> teams = FXCollections.observableArrayList();

    public DummyTeams() {
        Team team1 = new Team();
        team1.setName("team1");
        Team team2 = new Team();
        team2.setName("team2");
        Team team3 = new Team();
        team3.setName("team3");
        Team team4 = new Team();
        team4.setName("team4");
        Team team5 = new Team();
        team5.setName("team5");
        TeamMember tm1 = new TeamMember(team1, new Employee(0, "Marek", "Marek", new Salary(new BigDecimal(3000)), "Junior Software Engineer"));
        TeamMember tm2 = new TeamMember(team1, new Employee(1, "Darek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm3 = new TeamMember(team1, new Employee(2, "Jarek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm4 = new TeamMember(team2, new Employee(3, "Jan", "Nowak", new Salary(new BigDecimal(6000)), "Senior Software Engineer"));
        TeamMember tm5 = new TeamMember(team3, new Employee(0, "Marek", "Marek", new Salary(new BigDecimal(3000)), "Junior Software Engineer"));
        TeamMember tm6 = new TeamMember(team3, new Employee(1, "Darek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm7 = new TeamMember(team3, new Employee(2, "Jarek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm8 = new TeamMember(team3, new Employee(3, "Jan", "Nowak", new Salary(new BigDecimal(6000)), "Senior Software Engineer"));
        TeamMember tm9 = new TeamMember(team3, new Employee(0, "Marek", "Marek", new Salary(new BigDecimal(3000)), "Junior Software Engineer"));
        TeamMember tm10 = new TeamMember(team4, new Employee(1, "Darek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm11 = new TeamMember(team4, new Employee(2, "Jarek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm12 = new TeamMember(team5, new Employee(3, "Jan", "Nowak", new Salary(new BigDecimal(6000)), "Senior Software Engineer"));
        TeamMember tm13 = new TeamMember(team5, new Employee(0, "Marek", "Marek", new Salary(new BigDecimal(3000)), "Junior Software Engineer"));
        TeamMember tm14 = new TeamMember(team5, new Employee(1, "Darek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm15 = new TeamMember(team5, new Employee(2, "Jarek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer"));
        TeamMember tm16 = new TeamMember(team5, new Employee(3, "Jan", "Nowak", new Salary(new BigDecimal(6000)), "Senior Software Engineer"));
        team1.addTeamMember(tm1);
        team1.addTeamMember(tm2);
        team1.addTeamMember(tm3);
        team2.addTeamMember(tm4);
        team3.addTeamMember(tm5);
        team3.addTeamMember(tm6);
        team3.addTeamMember(tm7);
        team3.addTeamMember(tm8);
        team3.addTeamMember(tm9);
        team4.addTeamMember(tm10);
        team4.addTeamMember(tm11);
        team5.addTeamMember(tm12);
        team5.addTeamMember(tm13);
        team5.addTeamMember(tm14);
        team5.addTeamMember(tm15);
        team5.addTeamMember(tm16);
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
    }

    public ObservableList<ITeam> getTeams() {
        return teams;
    }
}
