package pl.edu.agh.iet.to2.teams.controller;

import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.view.TeamView;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class TeamOverviewController {

    private Team team;
    private AnchorPane pane;
    private TeamView view;


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


    //private Members members; // team.getMembers() - because we can't just SimpleProperty<Members> in team since it
    // wouldn't register add/remove method results... but on the other hand, neither will Set<Person>, which is what Members class
    // actually wraps. So, that remains to be thought of.

    public void initialize(){ // - invoke this method via FXML file (or manually in some application's main method (not recommended))
        team.getManagerProperty().addListener((o, oldVal, newVal) -> {
            // code to describe what happens after Manager property has changed
        });

        team.getProjectProperty().addListener((o, oldVal, newVal) -> {
            // code to describe what happens after Project property has changed
        });

        team.getMembers().getMembersProperty().addListener((o, oldVal, newVal) -> {
           // code to describe what happens after Members property has changed
            // (so if some members are added/removed this is invoked)

            System.out.print("member changed\n");
            System.out.print(team.hashCode()+"\n");
            System.out.print(oldVal+"\n");
            System.out.print(newVal+"\n");
        });

    }

    private TeamOverviewController(Team team, AnchorPane pane){
        this.pane = pane;
        this.team = team;
    }

    public static TeamOverviewController createControllerOn(Team team, AnchorPane pane){
        return new TeamOverviewController(team, pane);
    }


}
