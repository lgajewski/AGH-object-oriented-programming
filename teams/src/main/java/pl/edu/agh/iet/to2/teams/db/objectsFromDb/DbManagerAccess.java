package pl.edu.agh.iet.to2.teams.db.objectsFromDb;

import pl.edu.agh.iet.to2.teams.db.SqlHelper;

/**
 * Created by Iza on 14-02-2016.
 */
public class DbManagerAccess {
    public static void deleteManagerByPersonId(long personId){
        String query1 = "DELETE FROM `Manager` WHERE personId="+personId;

        SqlHelper.executeQuery(query1);
    }
}
