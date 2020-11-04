package com.tjgwebservices.tjgxmlcms.dbo.schools;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.school.Lecturer;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LecturerDBO extends DatabaseObject{
    

    public static void saveSQLLecturer(Lecturer lecturer) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Lecturer(lecturerName) VALUES(?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql); 
                pstmt.setString(1,lecturer.getLecturerName());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Lecturer> loadLecturers(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Lecturer> lecturerList = new ArrayList<>();
            String sql = "SELECT id,lecturerName FROM Lecturer;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);                
                       while(rs.next()){
                           Lecturer lecturer = new Lecturer();
                           lecturer.setId(rs.getInt("id"));
                           lecturer.setLecturerName(rs.getString("lecturerName"));
                           lecturerList.add(lecturer);
                       }
                tx.commit();
                session.close();
                return lecturerList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
