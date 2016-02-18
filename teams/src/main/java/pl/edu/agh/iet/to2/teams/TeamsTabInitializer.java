package pl.edu.agh.iet.to2.teams;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.app.TabInitializer;
import pl.edu.agh.iet.to2.teams.common.TeamData;
import pl.edu.agh.iet.to2.teams.common.TeamsModelManipulator;
import pl.edu.agh.iet.to2.teams.controller.MainController;
import pl.edu.agh.iet.to2.teams.controller.RootController;
import pl.edu.agh.iet.to2.teams.controller.TeamViewController;
import pl.edu.agh.iet.to2.teams.model.TeamsTree;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.DialogView;
import pl.edu.agh.iet.to2.teams.view.TeamView;

import java.io.IOException;
import java.util.HashMap;

public class TeamsTabInitializer implements TabInitializer {

    public AnchorPane pane;
    public MainController mainController;
    public TeamsModelManipulator manipulator;
    public TeamsTree teamsTree;
    public TeamView teamView;
    public DialogView dialogView;
    public TeamData database;

    @Override
    public Pane initialize(Presenter presenter, ModuleManager moduleManager) throws IOException {

        this.pane = new AnchorPane();

        this.dialogView = new DialogView();
        this.teamView = new TeamView(this.pane, this.dialogView);

        HashMap<String, ComponentView> allViews = new HashMap<>();
        allViews.put("TeamView", teamView);
        allViews.put("DialogView", dialogView);

        mainController = new MainController(allViews, this.pane);
        teamsTree = new TeamsTree();

        manipulator = new TeamsModelManipulator(teamsTree, mainController, (TeamView) allViews.get("TeamView"), null);
        database = new TeamData(manipulator);
        manipulator.setDatabase(database);

        mainController.addController(TeamViewController.createControllerOn(pane, (TeamView) allViews.get("TeamView"), manipulator, teamsTree, database));
        mainController.addController(RootController.createControllerOn(teamsTree, pane, (TeamView) allViews.get("TeamView")));

        try{
            database.getRootManagerFromDb();
        } catch (Exception e){
            System.out.println("Wyjątek!");
            e.printStackTrace();
        }

        return pane;
    }
}
