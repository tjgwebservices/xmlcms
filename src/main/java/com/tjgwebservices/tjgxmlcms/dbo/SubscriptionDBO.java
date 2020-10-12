package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscription;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDBO extends DatabaseObject {
    public static void saveSubscription(SocketSubscription subscription) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Subscription(subscriptionPlan,publisher,topic) VALUES(?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);                
                pstmt.setString(1,subscription.getSubscriptionPlan());
                pstmt.setString(2,subscription.getPublisher());
                pstmt.setString(3,subscription.getTopic());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<SocketSubscription> loadSubscriptions(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<SocketSubscription> subscriptionList = new ArrayList<SocketSubscription>();
            String sql = "SELECT subscriptionPlan, publisher, topic FROM Subscription;";
            try  {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);                
                       while(rs.next()){
                           SocketSubscription subscription = new SocketSubscription();
                           subscription.setId(rs.getInt("id"));
                           subscription.setSubscriptionPlan(rs.getString("subscriptionPlan"));
                           subscription.setPublisher(rs.getString("publisher"));
                           subscription.setTopic(rs.getString("topic"));
                           subscriptionList.add(subscription);
                       }
                tx.commit();
                session.close();
                return subscriptionList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
            return subscriptionList;
    }   
    
}
