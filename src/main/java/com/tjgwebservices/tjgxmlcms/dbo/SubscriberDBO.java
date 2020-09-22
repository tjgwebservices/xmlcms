package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
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

public class SubscriberDBO {
    public static void saveSubscriber(SocketSubscriber subscriber) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO Subscriber(subscriber) VALUES(?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,subscriber.getSubscriber());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<SocketSubscriber> loadSubscribers(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<SocketSubscriber> subscriberList = new ArrayList<SocketSubscriber>();
            String sql = "SELECT subscriber FROM Subscriber;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           SocketSubscriber subscriber = new SocketSubscriber();
                           subscriber.setId(rs.getInt("id"));
                           subscriber.setSubscriber(rs.getString("subscriber"));
                           subscriberList.add(subscriber);
                       }
                return subscriberList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
            return subscriberList;
    }   
}
