package pl.edu.agh.iet.to2.teams.db.objectsFromDb;

import pl.edu.agh.iet.to2.teams.db.SqlHelper;

/**
 * Created by Iza on 14-02-2016.
 */
public class DbTeamAccess {
    public static void deleteTeamByTeamId(long teamId){
        String query1 = "DELETE FROM `Team` WHERE personId="+teamId;

        SqlHelper.executeQuery(query1);
    }
}
