package com.tjgwebservices.tjgxmlcms.dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAdmin {
    public static void startDatabase(){
        String sql = "ATTACH DATABASE 'articledb.db' AS articledb;";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:?cache=shared");
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sql = "CREATE TABLE IF NOT EXISTS Article (\n"
                + " id integer PRIMARY KEY,\n"
                + " author text NOT NULL,\n"
                + " authorDate text NOT NULL,\n"
                + " title text NOT NULL,\n"
                + " description text NOT NULL,\n"
                + " content text NOT NULL);";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sql = "CREATE TABLE IF NOT EXISTS Subscriber (\n"
                + " id integer PRIMARY KEY,\n"
                + " subscriber text NOT NULL);";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sql = "CREATE TABLE IF NOT EXISTS Subscription (\n"
                + " id integer PRIMARY KEY,\n"
                + " subscriptionPlan text NOT NULL,\n"
                + " publisher text NOT NULL,\n"
                + " topic text NOT NULL);";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        
    }
}
