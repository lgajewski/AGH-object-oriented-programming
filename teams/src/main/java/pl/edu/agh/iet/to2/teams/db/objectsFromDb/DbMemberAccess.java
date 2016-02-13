package pl.edu.agh.iet.to2.teams.db.objectsFromDb;

import pl.edu.agh.iet.to2.teams.db.SqlHelper;
import pl.edu.agh.iet.to2.teams.db.tables.DbMember;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Iza on 13-02-2016.
 */
public class DbMemberAccess {

    public static List<DbMember> getMemberByTeamId (long teamId) throws SQLException {
        String query = "select from 'Member' where teamId=" + teamId;

        return ObjectRewriter.rewriteAsDbMember(SqlHelper.getResultSet(query, 3));
    }

    public static DbMember getMemberByPersonId (long personId) throws SQLException {
        String query = "select from 'Member' where personId=" + personId;

        List<DbMember> result = ObjectRewriter.rewriteAsDbMember(SqlHelper.getResultSet(query, 3));

        if(result.size()>1 || result.size()<1) {
            System.out.println("Some problems with db - there is wrong number of  members with personId ="+personId+": "+result.size());
            for(DbMember member : result){
                System.out.println(member.getMemberId()+", "+member.getPersonId()+", "+member.getTeamId());
            }
            System.out.println("Returning first occurrence");
        }

        return result.get(0);
    }
}
