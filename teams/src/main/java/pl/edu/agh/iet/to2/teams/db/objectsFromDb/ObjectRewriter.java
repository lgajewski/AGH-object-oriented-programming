package pl.edu.agh.iet.to2.teams.db.objectsFromDb;

import pl.edu.agh.iet.to2.teams.db.tables.DbManager;
import pl.edu.agh.iet.to2.teams.db.tables.DbMember;
import pl.edu.agh.iet.to2.teams.db.tables.DbPerson;
import pl.edu.agh.iet.to2.teams.db.tables.DbTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iza on 13-02-2016.
 */
public class ObjectRewriter {
    public static List<DbMember> rewriteAsDbMember (List<List> allTable) throws SQLException {
        List allRows = new ArrayList<DbMember>();

        for(List rs : allTable ){
            allRows.add(new DbMember(((Number) rs.get(0)).longValue(), ((Number) rs.get(1)).longValue(), ((Number) rs.get(2)).longValue()));
        }

        return allRows;
    }

    public static List<DbTeam> rewriteAsDbTeam (List<List> allTable) throws SQLException {
        List allRows = new ArrayList<DbTeam>();

        for(List rs : allTable ){
            allRows.add(new DbTeam(((Number) rs.get(0)).longValue(), rs.get(1).toString(), ((Number) rs.get(2)).longValue()));
        }

        return allRows;
    }

    private static List<DbManager> rewriteAsDbManager (ResultSet rs) throws SQLException {
        List allRows = new ArrayList<DbManager>();

        while ( rs.next() ){
            allRows.add(new DbManager(rs.getLong("managerId"), rs.getLong("personId"), rs.getLong("parentManagerId")));
        }

        return allRows;
    }

    private static List<DbPerson> rewriteAsDbPerson (ResultSet rs) throws SQLException {
        List allRows = new ArrayList<DbManager>();

        while ( rs.next() ){//personId, String name, String position, String date
            allRows.add(new DbPerson(rs.getLong("personId"), rs.getString("name"), rs.getString("position"), rs.getString("date")));
        }

        return allRows;
    }
}
