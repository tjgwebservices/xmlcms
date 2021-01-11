package com.tjgwebservices.tjgxmlcms.dbo.article;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.article.Configuration;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDBO extends DatabaseObject{

    public static void saveConfiguration(Configuration configuration){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(configuration);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLConfiguration(Configuration configuration) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Configuration(messages, views, shares, users, dateTime) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,configuration.getMessages());
                pstmt.setInt(2,configuration.getViews());
                pstmt.setInt(3,configuration.getShares());
                pstmt.setInt(4,configuration.getUsers());
                pstmt.setString(5,configuration.getDateTime());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateConfiguration(Configuration configuration) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Configuration SET messages = ?, views = ?, shares = ?, users =?, dateTime=? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,configuration.getMessages());
                pstmt.setInt(2,configuration.getViews());
                pstmt.setInt(3,configuration.getShares());
                pstmt.setInt(4,configuration.getUsers());
                pstmt.setString(5,configuration.getDateTime());
                pstmt.setInt(6,configuration.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Configuration> loadConfigurations(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Configuration> configurationList = new ArrayList<>();
            String sql = "SELECT id,messages,views,shares,users,dateTime FROM Configuration;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Configuration configuration = new Configuration();
                           configuration.setId(rs.getInt("id"));
                           configuration.setMessages(rs.getString("messages"));
                           configuration.setViews(rs.getInt("views"));
                           configuration.setShares(rs.getInt("shares"));
                           configuration.setUsers(rs.getInt("users"));
                           configuration.setDateTime(rs.getString("dateTime"));
                           configurationList.add(configuration);
                       }
                tx.commit();
                session.close();
                return configurationList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return new ArrayList<Configuration>();
    }
    
}
