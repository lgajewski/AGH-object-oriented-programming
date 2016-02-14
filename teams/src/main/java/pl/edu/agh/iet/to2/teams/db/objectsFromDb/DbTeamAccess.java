package pl.edu.agh.iet.to2.teams.db.objectsFromDb;

import pl.edu.agh.iet.to2.teams.db.SqlHelper;
import pl.edu.agh.iet.to2.teams.db.tables.DbTeam;

import java.util.List;

/**
 * Created by Iza on 14-02-2016.
 */
public class DbTeamAccess {
    public static void deleteTeamByTeamId(long teamId){
        String query1 = "DELETE FROM `Team` WHERE personId="+teamId;

        SqlHelper.executeQuery(query1);
    }

    public static List<DbTeam> getTeamByManagerId(long managerId) {
        return null;
    }

    public static void updateTeamNameByTeamId (long teamId, String name){
        String query =  "UPDATE Team\n" +
                "SET name='"+name+"'\n" +
                "WHERE teamId='"+teamId+"'";

        SqlHelper.executeQuery(query);
    }
}
