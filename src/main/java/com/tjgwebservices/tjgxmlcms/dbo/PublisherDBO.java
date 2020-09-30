package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.Publisher;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscription;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PublisherDBO {
    public static void savePublisher(Publisher publisher) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO Publisher(publisherName) VALUES(?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,publisher.getPublisherName());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Publisher> loadPublishers(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<Publisher> publisherList = new ArrayList<Publisher>();
            String sql = "SELECT publisher FROM Publisher;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           Publisher publisher = new Publisher();
                           publisher.setId(rs.getInt("id"));
                           publisher.setPublisherName(rs.getString("publisherName"));
                           publisherList.add(publisher);
                       }
                return publisherList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
            return publisherList;
    }   
    
}
