package com.wawrow.startup.network.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Wawrów on 08.12.2017.
 */
public class SQL {
    public SQL(String query){
        Connection conn = null;

        try
        {
            String userName = "pi";
            String password = "0eedec94ad02b";
            String sterownik = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost/phpmyadmin";
            Class.forName (sterownik);
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");
            //Commit Test
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }

    }
}
