package com.tjgwebservices.tjgxmlcms.dbo.calendar;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.calendar.EventAdministrator;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventAdministratorDBO {

    public static void saveEventAdministrator(EventAdministrator eventAdministrator){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(eventAdministrator);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLEventAdministrator(EventAdministrator eventAdministrator) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO EventAdministrator(administratorName, title, subTitle, contactInfo, eventId) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,eventAdministrator.getAdministratorName());
                pstmt.setString(2,eventAdministrator.getTitle());
                pstmt.setString(3,eventAdministrator.getSubTitle());
                pstmt.setString(4,eventAdministrator.getContactInfo());
                pstmt.setInt(5,eventAdministrator.getEventId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateEventAdministrator(EventAdministrator eventAdministrator) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE EventAdvertisement SET administratorName =?, title =?, subTitle =?, contactInfo =?, eventId =? WHERE id = ?";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,eventAdministrator.getAdministratorName());
                pstmt.setString(2,eventAdministrator.getTitle());
                pstmt.setString(3,eventAdministrator.getSubTitle());
                pstmt.setString(4,eventAdministrator.getContactInfo());
                pstmt.setInt(5,eventAdministrator.getEventId());
                pstmt.setInt(6,eventAdministrator.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<EventAdministrator> loadEventAdministrators(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<EventAdministrator> eventAdministratorList = new ArrayList<>();
            String sql = "SELECT id,administratorName,title,subTitle,contactInfo,eventId FROM EventAdministrator;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           EventAdministrator eventAdministrator = new EventAdministrator();
                           eventAdministrator.setId(rs.getInt("id"));
                           eventAdministrator.setAdministratorName(rs.getString("administratorName"));
                           eventAdministrator.setTitle(rs.getString("title"));
                           eventAdministrator.setSubTitle(rs.getString("subTitle"));
                           eventAdministrator.setContactInfo(rs.getString("contactInfo"));
                           eventAdministrator.setEventId(rs.getInt("eventId"));
                           eventAdministratorList.add(eventAdministrator);
                       }
                tx.commit();
                session.close();
                return eventAdministratorList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
