package pl.edu.agh.iet.to2.teams.controller;

import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.view.TeamView;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class TeamController implements SubController {

    private int childHashcode;
    private Team team;
    private AnchorPane pane;
    private TeamView view;


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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }



    public void initialize(){ //
      /*  team.getManagerProperty().addListener((o, oldVal, newVal) -> {

        });*/

        team.getProjectProperty().addListener((o, oldVal, newVal) -> {

        });

        team.getMembers().getMembersProperty().addListener((o, oldVal, newVal) -> {
            view.redrawTeam(view.tree.getRoot(), team.hashCode(), team);
            System.out.print("member changed\n");
        });

    }

    private TeamController(Team team, AnchorPane pane, TeamView view){
        this.pane = pane;
        this.team = team;
        this.childHashcode = team.hashCode();
        this.view = view;
    }

    public static TeamController createControllerOn(Team team, AnchorPane pane, TeamView view){
        TeamController tc = new TeamController(team, pane, view);
        tc.initialize();
        return tc;
    }


}