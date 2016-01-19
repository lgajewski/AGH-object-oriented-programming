package pl.edu.agh.iet.to2.teams.src.controller;

import api.team.Team;
import view.MainView;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class TeamOverviewController {

    private Team team;
    private MainView view;
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
            System.out.print(o);
        });

    }

    private TeamOverviewController(Team team){
        this.team = team;
    }

    public static TeamOverviewController createControllerOn(Team team){
        return new TeamOverviewController(team);
    }


}
