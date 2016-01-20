package pl.edu.agh.iet.to2.teams.common;

import pl.edu.agh.iet.to2.teams.api.person.Manager;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.db.SqlHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class TeamData {
    Set<Team> teams;
    Set<DBManager> managers;


    public Set<Team> getAllTeamsFromDb(){
        String query = "SELECT * FROM TEAM";
        List<List> rs = SqlHelper.getResultSet(query, 3);

        Set teams = new HashSet<Team>();

        for(List row : rs){
            Team team = Team.createTeam();
            team.setId((long) row.get(0));
            team.setName(row.get(1).toString());
            team.setProject(null);
            //team.setManager(getManager((long) row.get(2)));
            teams.add(team);
        }
        return teams;
    }


    public class DBManager{
        long id;
        long superiorId;
        // id = person Id, we don't need specific "manager Id"

        public DBManager(long id, long superiodId){
            this.id = id;
            this.superiorId = superiodId;
        }
    }

    public Set<Manager> getAllManagersFromDb(){
        String query = "SELECT * FROM Manager";
        List<List> rs = SqlHelper.getResultSet(query, 3);

        DBManager manager;

        // obtain ids
        for(List row : rs){

            manager = new DBManager((long)row.get(1), //personId
                    (long)row.get(2));//parentManagerId
            managers.add(manager);
        }

        // obtain subordinates/superiors


        return null;

    }

    public TreeMap getAllTeams(){
        TreeMap k = new TreeMap();




        return null;
    }

}
