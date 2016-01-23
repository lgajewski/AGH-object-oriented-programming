package pl.edu.agh.iet.to2.teams;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
import pl.edu.agh.iet.to2.teams.view.CustomTreeObject;
import pl.edu.agh.iet.to2.teams.view.TeamView;

import java.io.IOException;

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
        this.teamView.initialize();

        this.buttonView = new ButtonView(pane);
        this.buttonView.initialize();

        mainController = new MainController(this.teamView, this.pane);
        teamsTree = new TeamsTree();
        manipulator = new TeamsModelManipulator(teamsTree, mainController);

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


        TeamManager Boss = new TeamManager(0, "Jan Kowalski", "CEO");
        manipulator.addTeamManager(0, Boss);
        this.teamView.tree.getRoot().getChildren().add(new TreeItem<CustomTreeObject>(new CustomTreeObject(Boss.hashCode(), Boss.toString())));

        //mainController.addController(TeamManagerController.createControllerOn(Boss, pane, this.view));

        Team RootTeam = Team.createTeam(0);
        mainController.addController(TeamController.createControllerOn(RootTeam, pane, this.teamView));
        Boss.addTeam(RootTeam);

        TesterPerson mac1 = new TesterPerson(1, "Maciek0");
        RootTeam.getMembers().add(mac1);
        RootTeam.getMembers().add(new TesterPerson(2, "Maciek1"));

        TeamManager man1 = new TeamManager(3, "Manager0", "asd");
        mainController.addController(TeamManagerController.createControllerOn(man1, pane, this.teamView));
        Boss.addManager(man1);

        RootTeam.getMembers().remove(mac1);

        return pane;
    }
}
