package pl.edu.agh.iet.to2.projects.dummy;

import javafx.collections.ObservableList;
import pl.edu.agh.iet.to2.teams.ITeam;

public interface ITeamsSource {
    ObservableList<ITeam> getTeams();
}
