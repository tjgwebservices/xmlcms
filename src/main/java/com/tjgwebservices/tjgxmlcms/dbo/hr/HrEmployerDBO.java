package com.tjgwebservices.tjgxmlcms.dbo.hr;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.hr.HrEmployer;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HrEmployerDBO extends DatabaseObject {

    public static void saveSQLHrEmployer(HrEmployer hrEmployer) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO HrEmployer(employerName,employerContact,employerContactType,employerContactInfo,hrGroupId) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,hrEmployer.getEmployerName());
                pstmt.setString(2,hrEmployer.getEmployerContact());
                pstmt.setString(3,hrEmployer.getEmployerContactType());
                pstmt.setString(4,hrEmployer.getEmployerContactInfo());
                pstmt.setInt(5,hrEmployer.getHrGroupId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateHrEmployer(HrEmployer hrEmployer) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE HrEmployer SET employerName=?,employerContact=?,employerContactType=?,employerContactInfo=?,hrGroupId=? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,hrEmployer.getEmployerName());
                pstmt.setString(2,hrEmployer.getEmployerContact());
                pstmt.setString(3,hrEmployer.getEmployerContactType());
                pstmt.setString(4,hrEmployer.getEmployerContactInfo());
                pstmt.setInt(5,hrEmployer.getHrGroupId());
                pstmt.setInt(6,hrEmployer.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<HrEmployer> loadHrEmployers(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<HrEmployer> hrEmployerList = new ArrayList<>();
            String sql = "SELECT id,employerName,employerContact,employerContactType,employerContactInfo,hrGroupId FROM HrEmployer;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           HrEmployer hrEmployer = new HrEmployer();
                           hrEmployer.setId(rs.getInt("id"));
                           hrEmployer.setEmployerName(rs.getString("employerName"));
                           hrEmployer.setEmployerContact(rs.getString("employerContact"));
                           hrEmployer.setEmployerContactType(rs.getString("employerContactType"));
                           hrEmployer.setEmployerContactInfo(rs.getString("employerContactInfo"));
                           hrEmployer.setHrGroupId(rs.getInt("hrGroupId"));
                           hrEmployerList.add(hrEmployer);
                       }
                tx.commit();
                session.close();
                return hrEmployerList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
