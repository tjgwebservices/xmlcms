package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
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
    public static void saveSubscription(SocketSubscription subscription) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO Subscription(subscriptionPlan,publisher,topic) VALUES(?,?,?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,subscription.getSubscriptionPlan());
                pstmt.setString(2,subscription.getPublisher());
                pstmt.setString(1,subscription.getTopic());
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
            String sql = "SELECT subscriptionPlan, publisher, topic FROM Subscription;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           SocketSubscription subscription = new SocketSubscription();
                           subscription.setId(rs.getInt("id"));
                           subscription.setSubscriptionPlan(rs.getString("subscriptionPlan"));
                           subscription.setPublisher(rs.getString("publisher"));
                           subscription.setTopic(rs.getString("topic"));
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
