package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.School;
import com.tjgwebservices.tjgxmlcms.model.Student;
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

public class SchoolDBO {

    public static void saveSQLSchool(School school) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO School(schoolName,schoolLecturer) VALUES(?,?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,school.getSchoolName());
                pstmt.setString(2,school.getSchoolLecturer());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<School> loadSchools(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<School> schoolList = new ArrayList<>();
            String sql = "SELECT id,schoolName,schoolLecturer FROM School;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           School school = new School();
                           school.setId(rs.getInt("id"));
                           school.setSchoolName(rs.getString("schoolName"));
                           school.setSchoolLecturer(rs.getString("schoolLecturer"));
                           schoolList.add(school);
                       }
                return schoolList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
