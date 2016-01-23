package pl.edu.agh.iet.to2.teams;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.app.TabInitializer;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.person.TesterPerson;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.common.TeamsModelManipulator;
import pl.edu.agh.iet.to2.teams.controller.MainController;
import pl.edu.agh.iet.to2.teams.controller.TeamController;
import pl.edu.agh.iet.to2.teams.controller.TeamManagerController;
import pl.edu.agh.iet.to2.teams.model.TeamsTree;
import pl.edu.agh.iet.to2.teams.view.ButtonView;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.CustomTreeObject;
import pl.edu.agh.iet.to2.teams.view.TeamView;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class TeamsTabInitializer implements TabInitializer {

  //  TeamManagerController controller;
    public AnchorPane pane;
    public MainController mainController;
    public TeamsModelManipulator manipulator;
    public TeamsTree teamsTree;
    public TeamView teamView;
    public ButtonView buttonView;

    @Override
    public Pane initialize(Presenter presenter, ModuleManager moduleManager) throws IOException {

        this.pane = new AnchorPane();

        this.teamView = new TeamView(pane);
        this.buttonView = new ButtonView(pane);

        HashMap<String, ComponentView> allViews = new HashMap<>();
        allViews.put("TeamView", teamView);
        allViews.put("ButtonView", buttonView);

        mainController = new MainController(allViews, this.pane);
        teamsTree = new TeamsTree();

        manipulator = new TeamsModelManipulator(teamsTree, mainController, (TeamView) allViews.get("TeamView"));

       // manipulator.addTeam(this.teamsTree.find())


      /*  TextField nameField = new TextField("Default name");
        TextField occupationField = new TextField("Occupation");
        TextField

        HBox hbox = new HBox(8);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(new TextField);

        VBox vbox = new VBox(8);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(new Button("Cut"), new Button("Copy"), new Button("Paste"));
        pane.getChildren().add(vbox);*/


        TeamManager boss = new TeamManager(1, "Jan Kowalski", "CEO");
        manipulator.addTeamManager(0, boss);

        Team team0 = Team.createTeam(0);
        manipulator.addTeam(boss.getId(), team0);

        TesterPerson mac1 = new TesterPerson(2, "Maciek0", "worker");
        manipulator.addMember(team0.getId(), mac1);
        manipulator.addMember(team0.getId(), new TesterPerson(3, "Maciek1", "worker"));

        TeamManager manager0 = new TeamManager(4, "Manager0", "manager");
        manipulator.addTeamManager(boss.getId(), manager0);


        return pane;
    }
}
