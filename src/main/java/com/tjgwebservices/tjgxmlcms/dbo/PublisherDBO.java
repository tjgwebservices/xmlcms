package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.Publisher;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PublisherDBO extends DatabaseObject{
    public static void savePublisher(Publisher publisher) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Publisher(publisherName) VALUES(?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,publisher.getPublisherName());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Publisher> loadPublishers(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Publisher> publisherList = new ArrayList<Publisher>();
            String sql = "SELECT publisher FROM Publisher;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Publisher publisher = new Publisher();
                           publisher.setId(rs.getInt("id"));
                           publisher.setPublisherName(rs.getString("publisherName"));
                           publisherList.add(publisher);
                       }
                tx.commit();
                session.close();
                return publisherList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
            return publisherList;
    }   
    
}
