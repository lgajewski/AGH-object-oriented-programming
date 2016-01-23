package pl.edu.agh.iet.to2.teams.common;

import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.db.SqlHelper;

import java.util.List;

public class TeamData {
    //all that matters is below
    //you can use methods which are over this, but please continue my tree below and after that delete all trashes
    //i have created not implemented method below to you, so that you can continue it
    //~Iz

    public boolean getTeamsTree(TeamsModelManipulator teamsModelManipulator) throws Exception {
        String query1 = "SELECT * FROM Manager WHERE parentManagerId=NULL";
        List<List> rs = SqlHelper.getResultSet(query1, 3);
        if(rs.size()==0) return false;
        else if(rs.size()>1) {
            throw new Exception("Check db - there are many roots");
        }
        else {
            long id = (long)rs.get(0).get(1);
            createManager(teamsModelManipulator, 0, id);
            return true;
        }
    }

    private void createManager(TeamsModelManipulator teamsModelManipulator, long parentId, long personId) {
        String getPerson = "SELECT * FROM Person WHERE personId=" + personId;
        List<List> person =  SqlHelper.getResultSet(getPerson, 4);
        TeamManager tm = new TeamManager(personId, person.get(0).get(1).toString(),person.get(0).get(2).toString() );

        teamsModelManipulator.addTeamManager(parentId, tm);

        long managerId = (long) person.get(0).get(0);

        String getManagerSubordinates = "SELECT * FROM Manager WHERE parentManagerId=" + managerId;
        List<List> managerSubordinates = SqlHelper.getResultSet(getManagerSubordinates, 3);

        for(List manager : managerSubordinates){
            createManager(teamsModelManipulator, personId, (long) manager.get(1));
        }

        String getTeamSubordinates = "SELECT * FROM Team WHERE managerId=" + managerId;
        List<List> teamSubordinates = SqlHelper.getResultSet(getTeamSubordinates, 3);

        for(List team : teamSubordinates){
            createTeam(teamsModelManipulator, personId, (long) team.get(0));
        }

        //return tm;
    }

    private void createTeam(TeamsModelManipulator teamsModelManipulator, long parentId, long teamId) {
        String getTeam = "SELECT * FROM Team WHERE teamId="+teamId;
        List<List> teamsList = SqlHelper.getResultSet(getTeam, 3);

        Team team = Team.createTeam(teamId);
        team.setName(teamsList.get(0).get(1).toString());

        teamsModelManipulator.addTeam(parentId, team);

        String members = "SELECT * FROM Member WHERE teamId="+teamId;
        List<List> membersList = SqlHelper.getResultSet(getTeam, 3);

        for( List member : membersList){
            createMember(teamsModelManipulator, teamId, (long) member.get(1));
        }
    }

    private void createMember(TeamsModelManipulator teamsModelManipulator, long teamId, long personId) {

    }

    //najpierw managerow
    //potem po kolei po nich (przechodzenie po drzewie)
    //i do samego dołu

}

