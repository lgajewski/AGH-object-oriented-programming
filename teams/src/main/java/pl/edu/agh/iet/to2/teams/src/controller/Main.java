package pl.edu.agh.iet.to2.teams.src.controller;

import api.team.Team;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MainView;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class Main extends Application{

    // this class shouldn't probably be but meh



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        MainView view = new MainView(primaryStage);
        view.initialize();

        Team tomAndJerry = Team.createTeam();

        TeamOverviewController toc = TeamOverviewController.createControllerOn(tomAndJerry);
        toc.initialize();

        // add hardcoding here, like members etc

       // tomAndJerry.getMembers().add(new TesterPerson(0, "Maciek0"));
       // tomAndJerry.getMembers().add(new TesterPerson(1, "Maciek1"));

    }
}
