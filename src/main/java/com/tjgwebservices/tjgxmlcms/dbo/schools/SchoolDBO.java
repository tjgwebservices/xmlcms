package com.tjgwebservices.tjgxmlcms.dbo.schools;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.school.School;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SchoolDBO extends DatabaseObject {

    public static void saveSQLSchool(School school) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO School(schoolName,schoolLecturer) VALUES(?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,school.getSchoolName());
                pstmt.setString(2,school.getSchoolLecturer());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<School> loadSchools(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<School> schoolList = new ArrayList<>();
            String sql = "SELECT id,schoolName,schoolLecturer FROM School;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                           School school = new School();
                           school.setId(rs.getInt("id"));
                           school.setSchoolName(rs.getString("schoolName"));
                           school.setSchoolLecturer(rs.getString("schoolLecturer"));
                           schoolList.add(school);
                       }
                tx.commit();
                session.close();
                return schoolList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
