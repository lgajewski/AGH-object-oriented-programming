package pl.edu.agh.iet.to2.teams.controller;

import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.view.TeamView;
import sun.plugin.javascript.navig.Anchor;

import java.util.HashSet;

/**
 * Created by maciek on 21.01.16.
 */
public class MainController {

    private HashSet<SubController> controllers;
    private TeamView teamView;
    private AnchorPane pane;

    public MainController(TeamView teamView, AnchorPane pane){
        this.teamView = teamView;
        this.pane = pane;
        this.initialize();
    }

    public TeamView getTeamView() {
        return teamView;
    }

    public void setTeamView(TeamView teamView) {
        this.teamView = teamView;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public HashSet<SubController> getControllers(){
        return controllers;
    }

    public void initialize() {
        controllers = new HashSet<SubController>();
    }

    public void addController(SubController controller){
        controllers.add(controller);
    }

    public void removeController(SubController controller){
        controllers.remove(controller);
    }

    public void removeControllerByHashcode(int childHashcode){
        for(SubController sc : controllers){
            if(sc.getChildHashcode() == childHashcode)
                controllers.remove(sc);
        }
    }
}
