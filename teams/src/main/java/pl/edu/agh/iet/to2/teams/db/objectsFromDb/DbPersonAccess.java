package pl.edu.agh.iet.to2.teams.db.objectsFromDb;

import pl.edu.agh.iet.to2.teams.db.SqlHelper;

/**
 * Created by Iza on 14-02-2016.
 */
public class DbPersonAccess {
    public static void deletePersonByPersonId(long personId){
        String query1 = "DELETE FROM `Person` WHERE personId="+personId;

        SqlHelper.executeQuery(query1);
    }
}
