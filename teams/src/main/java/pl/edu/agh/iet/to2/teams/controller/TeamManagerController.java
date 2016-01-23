package pl.edu.agh.iet.to2.teams.controller;

import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.TeamView;

/*
 /* Created by maciek on 20.01.16.
/**/

public class TeamManagerController implements SubController {

    private int childHashcode;
    private long childId;
    private TeamManager manager;
    private AnchorPane pane;
    private TeamView view;


    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    public int getChildHashcode() {
        return childHashcode;
    }

    public void setChildHashcode(int childHashcode) {
        this.childHashcode = childHashcode;
    }

    public TeamView getView() {
        return view;
    }

    public void setView(ComponentView view) {
        this.view = (TeamView) view;
    }

    public TeamManager getManager() {
        return this.manager;
    }

    public void setManager(TeamManager manager) {
        this.manager = manager;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }



    public void initialize(){
        this.manager.getManagersProperty().addListener((o, oldVal, newVal) -> {
            view.redrawManager(view.tree.getRoot(), manager.hashCode(), manager);
            System.out.print("Managers changes\n");
        });

        this.manager.getTeamsProperty().addListener((o, oldVal, newVal) -> {
            view.redrawManager(view.tree.getRoot(), manager.hashCode(), manager);
            System.out.print("Teams changes\n");
        });
    }

    private TeamManagerController(TeamManager manager, AnchorPane pane, TeamView view){
        this.pane = pane;
        this.manager = manager;
        this.childHashcode = manager.hashCode();
        this.view = view;
    }

    public static TeamManagerController createControllerOn(TeamManager manager, AnchorPane pane, TeamView view){
        TeamManagerController tmc = new TeamManagerController(manager, pane, view);
        tmc.initialize();
        return tmc;
    }



}
