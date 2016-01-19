package pl.edu.agh.iet.to2.teams.db;

import java.sql.*;

/**
 * Created by Iza on 2016-01-19.
 */
public class SqlHelper {
    private Connection c;
    private Statement stmt;
    private String path;


    private static SqlHelper ourInstance = new SqlHelper();

    public static SqlHelper getInstance() {
        return ourInstance;
    }

    private SqlHelper() {
        c = null;
        stmt = null;
        path = "jdbc:sqlite:teams\\src\\main\\java\\pl.edu.agh.iet.to2.teams\\db\\db2";
    }

    public void executeQuery (String sqlQuery){
        c  = null;
        stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(path);
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
            c = DriverManager.getConnection(path);
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
