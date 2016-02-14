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

    public static void updatePersonNameByPersonId(long personId, String name){
        String query =  "UPDATE Person\n" +
                        "SET name='"+name+"'\n" +
                        "WHERE personId='"+personId+"'";

        SqlHelper.executeQuery(query);
    }

    public static void updatePersonPositionByPersonId(long personId, String position){
        String query =  "UPDATE Person\n" +
                "SET position='"+position+"'\n" +
                "WHERE personId='"+personId+"'";

        SqlHelper.executeQuery(query);
    }
}
