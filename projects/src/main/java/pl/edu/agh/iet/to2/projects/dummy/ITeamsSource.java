package pl.edu.agh.iet.to2.projects.dummy;

import interfaces.ITeam;
import javafx.collections.ObservableList;

public interface ITeamsSource {
    ObservableList<ITeam> getTeams();
}
