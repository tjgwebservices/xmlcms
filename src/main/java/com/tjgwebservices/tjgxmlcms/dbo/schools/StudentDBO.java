package com.tjgwebservices.tjgxmlcms.dbo.schools;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.school.Student;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDBO extends DatabaseObject{

    public static void saveSQLStudent(Student student) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Student(lastName,firstName,courseId) VALUES(?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,student.getLastName());
                pstmt.setString(2,student.getFirstName());
                pstmt.setInt(3,student.getCourseId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Student> loadStudents(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Student> studentList = new ArrayList<>();
            String sql = "SELECT id,lastName,firstName,courseId FROM Student;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Student student = new Student();
                           student.setId(rs.getInt("id"));
                           student.setLastName(rs.getString("firstName"));
                           student.setFirstName(rs.getString("lastName"));
                           student.setCourseId(rs.getInt("courseId"));
                           studentList.add(student);
                       }
                tx.commit();
                session.close();
                return studentList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
