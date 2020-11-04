package com.tjgwebservices.tjgxmlcms.dbo.calendar;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.calendar.EventAdvertisement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventAdvertisementDBO extends DatabaseObject {

    public static void saveEventAdvertisement(EventAdvertisement eventAdvertisement){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(eventAdvertisement);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLEventAdvertisement(EventAdvertisement eventAdvertisement) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO EventAdvertisement(title, subTitle, adImagePath, contactInfo, eventId) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,eventAdvertisement.getTitle());
                pstmt.setString(2,eventAdvertisement.getSubTitle());
                pstmt.setString(3,eventAdvertisement.getAdImagePath());
                pstmt.setString(4,eventAdvertisement.getContactInfo());
                pstmt.setInt(5,eventAdvertisement.getEventId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateEventAdvertisement(EventAdvertisement eventAdvertisement) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE EventAdvertisement SET title =?, subTitle =?, adImagePath =?, contactInfo =?, eventId =? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,eventAdvertisement.getTitle());
                pstmt.setString(2,eventAdvertisement.getSubTitle());
                pstmt.setString(3,eventAdvertisement.getAdImagePath());
                pstmt.setString(4,eventAdvertisement.getContactInfo());
                pstmt.setInt(5,eventAdvertisement.getEventId());
                pstmt.setInt(6,eventAdvertisement.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<EventAdvertisement> loadEventAdvertisements(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<EventAdvertisement> eventAdvertisementList = new ArrayList<>();
            String sql = "SELECT id,title,subTitle,adImagePath,contactInfo,eventId FROM EventAdvertisement;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           EventAdvertisement eventAdvertisement = new EventAdvertisement();
                           eventAdvertisement.setId(rs.getInt("id"));
                           eventAdvertisement.setTitle(rs.getString("title"));
                           eventAdvertisement.setSubTitle(rs.getString("subTitle"));
                           eventAdvertisement.setAdImagePath(rs.getString("adImagePath"));
                           eventAdvertisement.setContactInfo(rs.getString("contactInfo"));
                           eventAdvertisement.setEventId(rs.getInt("eventId"));
                           eventAdvertisementList.add(eventAdvertisement);
                       }
                tx.commit();
                session.close();
                return eventAdvertisementList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
