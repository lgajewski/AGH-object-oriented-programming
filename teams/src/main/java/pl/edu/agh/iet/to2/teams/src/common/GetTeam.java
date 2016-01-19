package pl.edu.agh.iet.to2.teams.src.common;

import api.person.TeamManager;
import api.team.Team;

import java.util.List;

/**
 * Created by Pan Ciemnosci on 2016-01-11.
 */
public class GetTeam {

    private Team team;
    private TeamManager manager;

    public GetTeam(Team team, TeamManager leader){
        this.team = team;
        this.manager = leader;
    }

    public List members(){
        return null;
        // TODO : return some data structure containing representation of Persons/Employees in a way
        // that the others may need (so, either IDs or names or both, whatever is necessary for them)
    }

    public List leader(){
        return null;
        // TODO : like above but for what they need to be a leader
    }

    public String name(){
        return null;
    }

    public String forEmployee(long id){
      return null;
    }

}
