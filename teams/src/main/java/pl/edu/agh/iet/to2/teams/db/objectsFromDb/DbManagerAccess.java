package pl.edu.agh.iet.to2.teams.db.objectsFromDb;

import pl.edu.agh.iet.to2.teams.db.SqlHelper;
import pl.edu.agh.iet.to2.teams.db.tables.DbManager;

import java.util.List;

/**
 * Created by Iza on 14-02-2016.
 */
public class DbManagerAccess {
    public static void deleteManagerByPersonId(long personId){
        String query1 = "DELETE FROM `Manager` WHERE personId="+personId;

        SqlHelper.executeQuery(query1);
    }

    public static long getManagerIdByPersonId (long personId) {
        String query = "select * from Manager where personId="+personId;

        return ObjectRewriter.rewriteAsDbManager(SqlHelper.getResultSet(query, 3)).get(0).getManagerId();
    }

    public static List<DbManager> getManagerByParentManagerId (long parentManagerId){
        String query = "select * from Manager where parentManagerId="+parentManagerId;

        return ObjectRewriter.rewriteAsDbManager(SqlHelper.getResultSet(query, 3));
    }
}
