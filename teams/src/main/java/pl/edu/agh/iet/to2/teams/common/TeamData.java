package pl.edu.agh.iet.to2.teams.common;

import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.person.TesterPerson;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.db.SqlHelper;

import java.util.List;

public class TeamData {

    private TeamsModelManipulator teamsModelManipulator;

    public TeamData(TeamsModelManipulator teamsModelManipulator) {
        this.teamsModelManipulator = teamsModelManipulator;
    }


    public boolean getTeamsTree() throws Exception {
        String query1 = "SELECT * FROM Manager WHERE parentManagerId=NULL";
        List<List> rs = SqlHelper.getResultSet(query1, 3);
        if(rs.size()==0) return false;
        else if(rs.size()>1) {
            throw new Exception("Check db - there are many roots");
        }
        else {
            long id = (long)rs.get(0).get(1);
            createManager(0, id);
            return true;
        }
    }

    private void createManager( long parentId, long personId) {
        String getPerson = "SELECT * FROM Person WHERE personId=" + personId;
        List<List> person =  SqlHelper.getResultSet(getPerson, 4);
        TeamManager tm = new TeamManager(personId, person.get(0).get(1).toString(),person.get(0).get(2).toString() );

        teamsModelManipulator.addTeamManager(parentId, tm);

        long managerId = (long) person.get(0).get(0);

        String getManagerSubordinates = "SELECT * FROM Manager WHERE parentManagerId=" + managerId;
        List<List> managerSubordinates = SqlHelper.getResultSet(getManagerSubordinates, 3);

        for(List manager : managerSubordinates){
            createManager(personId, (long) manager.get(1));
        }

        String getTeamSubordinates = "SELECT * FROM Team WHERE managerId=" + managerId;
        List<List> teamSubordinates = SqlHelper.getResultSet(getTeamSubordinates, 3);

        for(List team : teamSubordinates){
            createTeam(personId, (long) team.get(0));
        }

        //return tm;
    }

    private void createTeam(long parentId, long teamId) {
        String getTeam = "SELECT * FROM Team WHERE teamId="+teamId;
        List<List> teamsList = SqlHelper.getResultSet(getTeam, 3);

        Team team = Team.createTeam(teamId);
        team.setName(teamsList.get(0).get(1).toString());

        teamsModelManipulator.addTeam(parentId, team);

        String members = "SELECT * FROM Member WHERE teamId="+teamId;
        List<List> membersList = SqlHelper.getResultSet(members, 3);

        for( List member : membersList){
            createMember(teamId, (long) member.get(1));
        }
    }

    private void createMember(long teamId, long personId) {
        String getMember = "SELECT * FROM Person WHERE personId="+personId;
        List<List> members = SqlHelper.getResultSet(getMember, 3);

        TesterPerson testerPerson = new TesterPerson((long) members.get(0).get(0), members.get(0).get(1).toString(), members.get(0).get(2).toString());

        teamsModelManipulator.addMember(teamId,testerPerson );
    }
}

