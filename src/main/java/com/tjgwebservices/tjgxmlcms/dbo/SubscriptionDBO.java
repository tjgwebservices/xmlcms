package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
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

public class SubscriptionDBO {
    public static void saveSubscription(SocketSubscriber subscriber) {
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

    public static List<SocketSubscription> loadSubscriptions(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<SocketSubscription> subscriptionList = new ArrayList<SocketSubscription>();
            String sql = "SELECT subscription FROM Subscription;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           SocketSubscription subscription = new SocketSubscription();
                           subscription.setId(rs.getInt("id"));
                           subscription.setSubscription(rs.getString("subscription"));
                           subscriptionList.add(subscription);
                       }
                return subscriptionList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
            return subscriptionList;
    }   
    
}
