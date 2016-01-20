package pl.edu.agh.iet.to2.teams;

import java.util.List;

public interface ITeamsModule {
    String getTeamForEmployeeId(long id);

    List<ITeam> getTeams();

    void init();
}
