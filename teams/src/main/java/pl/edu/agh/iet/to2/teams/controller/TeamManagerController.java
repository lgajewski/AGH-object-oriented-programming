package pl.edu.agh.iet.to2.teams.controller;

import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
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

    public void setView(TeamView view) {
        this.view = view;
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


    //private Members members; // team.getMembers() - because we can't just SimpleProperty<Members> in team since it
    // wouldn't register add/remove method results... but on the other hand, neither will Set<Person>, which is what Members class
    // actually wraps. So, that remains to be thought of.

    public void initialize(){ // - invoke this method via FXML file (or manually in some application's main method (not recommended))
        this.manager.getManagersProperty().addListener((o, oldVal, newVal) -> {
            // code to describe what happens after Manager property has changed
            view.redrawManager(view.tree.getRoot(), manager.hashCode(), manager);
            System.out.print("Managers changes\n");
        });

        this.manager.getTeamsProperty().addListener((o, oldVal, newVal) -> {
            // code to describe what happens after Project property has changed
            view.redrawManager(view.tree.getRoot(), manager.hashCode(), manager);
            System.out.print("Teams changes\n");
        });

       /* team.getMembers().getMembersProperty().addListener((o, oldVal, newVal) -> {
            // code to describe what happens after Members property has changed
            // (so if some members are added/removed this is invoked)
            view.redrawTeam(view.tree.getRoot(), team.hashCode(), team);
            System.out.print("member changed\n");
            //  System.out.print(team.hashCode()+"\n");
            //   System.out.print(oldVal+"\n");
            //   System.out.print(newVal+"\n");
        });*/

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
