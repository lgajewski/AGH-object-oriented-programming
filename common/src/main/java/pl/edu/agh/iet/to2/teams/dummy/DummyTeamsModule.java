package pl.edu.agh.iet.to2.teams.dummy;

import javafx.collections.FXCollections;
import pl.edu.agh.iet.to2.teams.TeamsModule;
import pl.edu.agh.iet.to2.teams.ITeam;

import java.util.List;

public class DummyTeamsModule implements TeamsModule {

    List<ITeam> teams = FXCollections.observableArrayList();

    public DummyTeamsModule() {
    }

    @Override
    public String getTeamForEmployeeId(long id) {
        return null;
    }

    @Override
    public List<ITeam> getTeams() {
        return teams;
    }

}
