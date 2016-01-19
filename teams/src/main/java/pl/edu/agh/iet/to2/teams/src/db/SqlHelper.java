package pl.edu.agh.iet.to2.teams.src.db;

import java.sql.*;

/**
 * Created by Iza on 2016-01-19.
 */
public class SqlHelper {
    private Connection c;
    private Statement stmt;


    private static SqlHelper ourInstance = new SqlHelper();

    public static SqlHelper getInstance() {
        return ourInstance;
    }

    private SqlHelper() {
        c = null;
        stmt = null;
    }

    public void executeQuery (String sqlQuery){
        c  = null;
        stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db2");
            stmt = c.createStatement();
            stmt.executeUpdate(sqlQuery);
            stmt.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(c!=null)
                    c.close();
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getResultSet (String sqlQuery){
        c  = null;
        stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db2");
            stmt = c.createStatement();
            return stmt.executeQuery( sqlQuery );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(c!=null)
                    c.close();
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
