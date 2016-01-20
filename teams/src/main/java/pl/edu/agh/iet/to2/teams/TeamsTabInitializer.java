package pl.edu.agh.iet.to2.teams;

import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.app.TabInitializer;
import pl.edu.agh.iet.to2.teams.api.person.TesterPerson;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.controller.TeamOverviewController;
import pl.edu.agh.iet.to2.teams.view.CustomTreeCellImpl;
import pl.edu.agh.iet.to2.teams.view.TeamView;

import java.io.IOException;

public class TeamsTabInitializer implements TabInitializer {

    TeamOverviewController controller;
    TeamView view;

    @Override
    public Pane initialize(Presenter presenter, ModuleManager moduleManager) throws IOException {
        AnchorPane pane = new AnchorPane();

        this.view = new TeamView(pane);
        this.view.initialize();

        Team RootTeam = Team.createTeam();
        System.out.println("1: " + RootTeam.hashCode());
        System.out.println("2: " + RootTeam.getMembers().hashCode());
        System.out.println("3: " + RootTeam.getMembers().getMembers().hashCode());
        System.out.println("4: " + RootTeam.getMembers().getMembersProperty().hashCode());

        controller = TeamOverviewController.createControllerOn(RootTeam, pane);
        controller.setView(this.view);
        controller.initialize();

        RootTeam.getMembers().add(new TesterPerson(0, "Maciek0"));
        RootTeam.getMembers().add(new TesterPerson(1, "Maciek1"));

        return pane;
    }
}
