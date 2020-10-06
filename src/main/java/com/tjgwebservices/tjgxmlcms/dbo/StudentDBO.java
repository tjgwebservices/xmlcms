package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.LectureNote;
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

public class StudentDBO {

    public static void saveSQLStudent(Student student) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO Student(lastName,firstName,courseId) VALUES(?,?,?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,student.getLastName());
                pstmt.setString(2,student.getFirstName());
                pstmt.setInt(3,student.getCourseId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Student> loadStudents(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<Student> studentList = new ArrayList<>();
            String sql = "SELECT id,lastName,firstName,courseId FROM Student;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           Student student = new Student();
                           student.setId(rs.getInt("id"));
                           student.setLastName(rs.getString("firstName"));
                           student.setFirstName(rs.getString("lastName"));
                           student.setCourseId(rs.getInt("courseId"));
                           studentList.add(student);
                       }
                return studentList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
