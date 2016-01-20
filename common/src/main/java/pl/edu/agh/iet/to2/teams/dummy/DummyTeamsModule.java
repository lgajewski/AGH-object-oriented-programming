package pl.edu.agh.iet.to2.teams.dummy;

import javafx.collections.FXCollections;
import pl.edu.agh.iet.to2.ModuleManager;
import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeesModuleImpl;
import pl.edu.agh.iet.to2.teams.ITeamMember;
import pl.edu.agh.iet.to2.teams.TeamsModule;
import pl.edu.agh.iet.to2.teams.ITeam;

import java.util.List;

public class DummyTeamsModule implements TeamsModule {

    List<ITeam> teams = FXCollections.observableArrayList();

    public DummyTeamsModule() {
    }

    @Override
    public String getTeamForEmployeeId(long id) {

        for(ITeam t : teams) {
            for(ITeamMember tm : t.getTeamMembers()) {
                if(tm.getEmployee().getId() == id) {
                    return t.getName();
                }
            }
        }

        return null;

    }

    @Override
    public List<ITeam> getTeams() {
        return teams;
    }


    @Override
    public void init() {

        EmployeesModule module = ModuleManager.getEmployeesModule();
        List<IEmployee> employees = module.getEmployees();
        ITeam team = new DummyTeam("Team1");
        for(int i = 0; i < employees.size(); i++) {
            if(i%2 == 0) {
                team = new DummyTeam("Team" + i);
                teams.add(team);
            }
            team.getTeamMembers().add(new DummyTeamMember(team, employees.get(i)));
        }

    }


}
