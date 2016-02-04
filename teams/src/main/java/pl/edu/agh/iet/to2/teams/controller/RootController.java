package pl.edu.agh.iet.to2.teams.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.model.TeamsTree;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.TeamView;

/**
 * Created by maciek on 02.02.16.
 */
public class RootController implements SubController{

    private int childHashcode;
    private long childId;
    private TeamsTree teamsTree;
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

    public TeamsTree getTeamsTree() {
        return this.teamsTree;
    }

    public void setTeamsTree(TeamsTree teamsTree) {
        this.teamsTree = teamsTree;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }



    public void initialize(){
        this.teamsTree.getRootProperty().addListener(new ChangeListener<TeamManager>(){
            @Override
            public void changed(ObservableValue<? extends TeamManager> observable, TeamManager oldValue, TeamManager newValue) {
                view.redrawRoot(null);
            }
        });
    }

    private RootController(TeamsTree teamsTree, AnchorPane pane, TeamView view){
        this.pane = pane;
        this.teamsTree = teamsTree;
        this.childHashcode = teamsTree.hashCode();
        this.view = view;
    }

    public static RootController createControllerOn(TeamsTree teamsTree, AnchorPane pane, TeamView view){
        RootController rc = new RootController(teamsTree, pane, view);
        rc.initialize();
        return rc;
    }



}
