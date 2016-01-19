package pl.edu.agh.iet.to2.teams.common;

import pl.edu.agh.iet.to2.teams.api.person.Manager;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.db.SqlHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class TeamData {
    Set<Team> teams;
    Set<Manager> managers;


    public Set<Team> getAllTeamsFromDb(){
        String query = "SELECT * FROM TEAM";
        List<List> rs = SqlHelper.getResultSet(query, 3);

        Set teams = new HashSet<Team>();

        for(List row : rs){
            Team team = Team.createTeam();
            team.setId((long) row.get(0));
            team.setName(row.get(1).toString());
            team.setProject(null);
            team.setManager(getManager((long) row.get(2)));
            teams.add(team);
        }
        return teams;
    }

    public Set<Manager> getAllManagersFromDb(){
        String query = "SELECT * FROM Manager";
        List<List> rs = SqlHelper.getResultSet(query, 3);

        for(List row : rs){
            Manager manager = new TeamManager((int) row.get(0), //managerId
                    row.get(1), //personId
                    row.get(2) //parentManagerId

            );
        }

    }

    public TreeMap getAllTeams(){
        TreeMap k = new TreeMap();




        return null;
    }

}
