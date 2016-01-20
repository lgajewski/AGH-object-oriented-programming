package pl.edu.agh.iet.to2.teams;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.app.TabInitializer;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.person.TesterPerson;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.controller.Controller;
import pl.edu.agh.iet.to2.teams.controller.TeamController;
import pl.edu.agh.iet.to2.teams.controller.TeamManagerController;
import pl.edu.agh.iet.to2.teams.view.CustomTreeObject;
import pl.edu.agh.iet.to2.teams.view.TeamView;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TeamsTabInitializer implements TabInitializer {

  //  TeamManagerController controller;
    public AnchorPane pane;
    public Set<Controller> controllers;
    TeamView view;

    @Override
    public Pane initialize(Presenter presenter, ModuleManager moduleManager) throws IOException {

        pane = new AnchorPane();
        controllers = new HashSet<Controller>();

        this.view = new TeamView(pane);
        this.view.initialize();

        TeamManager Boss = new TeamManager(0, "Jan Kowalski", "CEO");
        this.view.tree.getRoot().getChildren().add(new TreeItem<CustomTreeObject>(new CustomTreeObject(Boss.hashCode(), Boss.toString())));

        controllers.add(TeamManagerController.createControllerOn(Boss, pane, this.view));

        Team RootTeam = Team.createTeam();
        controllers.add(TeamController.createControllerOn(RootTeam, pane, this.view));
        Boss.addTeam(RootTeam);

        TesterPerson mac1 = new TesterPerson(1, "Maciek0");
        RootTeam.getMembers().add(mac1);
        RootTeam.getMembers().add(new TesterPerson(2, "Maciek1"));

        TeamManager man1 = new TeamManager(3, "Manager0", "asd");
        controllers.add(TeamManagerController.createControllerOn(man1, pane, this.view));
        Boss.addManager(man1);

        RootTeam.getMembers().remove(mac1);

        return pane;
    }
}
