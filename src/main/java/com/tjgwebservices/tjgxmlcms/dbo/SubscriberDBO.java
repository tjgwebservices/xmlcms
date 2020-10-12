package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscriberDBO extends DatabaseObject{
    public static void saveSubscriber(SocketSubscriber subscriber) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Subscriber(subscriber) VALUES(?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,subscriber.getSubscriber());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<SocketSubscriber> loadSubscribers(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<SocketSubscriber> subscriberList = new ArrayList<SocketSubscriber>();
            String sql = "SELECT subscriber FROM Subscriber;";
            try  {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);                       while(rs.next()){
                           SocketSubscriber subscriber = new SocketSubscriber();
                           subscriber.setId(rs.getInt("id"));
                           subscriber.setSubscriber(rs.getString("subscriber"));
                           subscriberList.add(subscriber);
                       }
                tx.commit();
                session.close();
                return subscriberList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
            return subscriberList;
    }   
}
