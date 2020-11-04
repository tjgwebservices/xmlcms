package com.tjgwebservices.tjgxmlcms.dbo.calendar;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.calendar.Event;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDBO  extends DatabaseObject{

    public static void saveEvent(Event event){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(event);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLEvent(Event event) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Event(title, startDate, endDate, location, description) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,event.getTitle());
                pstmt.setString(2,event.getStartDate());
                pstmt.setString(3,event.getEndDate());
                pstmt.setString(4,event.getLocation());
                pstmt.setString(5,event.getDescription());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateEvent(Event event) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Event SET title = ?, startDate = ?, endDate = ?, location =?, description =? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,event.getTitle());
                pstmt.setString(2,event.getStartDate());
                pstmt.setString(3,event.getEndDate());
                pstmt.setString(4,event.getLocation());
                pstmt.setString(5,event.getDescription());
                pstmt.setInt(6,event.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Event> loadEvents(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Event> eventList = new ArrayList<>();
            String sql = "SELECT id,title,startDate,endDate,location,description FROM Event;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Event event = new Event();
                           event.setId(rs.getInt("id"));
                           event.setTitle(rs.getString("title"));
                           event.setStartDate(rs.getString("startDate"));
                           event.setEndDate(rs.getString("endDate"));
                           event.setLocation(rs.getString("location"));
                           event.setDescription(rs.getString("description"));
                           eventList.add(event);
                       }
                tx.commit();
                session.close();
                return eventList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
