package pl.edu.agh.iet.to2.teams.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iza on 2016-01-19.
 */
public class SqlHelper {
    private static Connection c;
    private static Statement stmt;
    private static String path;


    private static SqlHelper ourInstance = new SqlHelper();

    public static SqlHelper getInstance() {
        return ourInstance;
    }

    private SqlHelper() {
        c = null;
        stmt = null;
        path = "jdbc:sqlite:teams\\src\\main\\java\\pl\\edu\\agh\\iet\\to2\\teams\\db\\db2";
        //@"F:\workspace\\teams\\src\\main\\java\\pl\\edu\\agh\\iet\\to2\\teams\\db\\db2"
    }

    public static void executeQuery (String sqlQuery){
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<List> getResultSet (String sqlQuery, int numberOfColumns){
        c  = null;
        stmt = null;
        List allRows = new ArrayList<List>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(path);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            allRows = rewriteResultSet(rs, numberOfColumns);
        } catch (Exception e) {
            System.out.println("Wyjatek w sql helper");
            e.printStackTrace();
        } finally {
            try {
                if(c!=null)
                    c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return allRows;
        }
    }

    private static List<List> rewriteResultSet (ResultSet rs, int numberOfColumns) throws Exception {
        List allRows = new ArrayList<List>();

        if(rs==null) throw new Exception ("result is null. it is saaaad :(");

        while ( rs.next() ) {
            List row = new ArrayList<>();
            for(int column = 1; column <= numberOfColumns; column++){
                row.add(rs.getObject(column));
            }
            allRows.add(row);
        }
        return allRows;
    }

}
