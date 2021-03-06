package com.tjgwebservices.tjgxmlcms.dbo.hr;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.hr.HrGroup;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HrGroupDBO extends DatabaseObject {
    
    public static void saveSQLHrGroup(HrGroup hrGroup) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO HrGroup(groupName) VALUES(?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,hrGroup.getGroupName());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateHrGroup(HrGroup hrGroup) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE HrGroup SET groupName = ? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,hrGroup.getGroupName());
                pstmt.setInt(2,hrGroup.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<HrGroup> loadHrGroups(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<HrGroup> hrGroupList = new ArrayList<>();
            String sql = "SELECT id,groupName FROM HrGroup;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           HrGroup hrGroup = new HrGroup();
                           hrGroup.setId(rs.getInt("id"));
                           hrGroup.setGroupName(rs.getString("groupName"));
                           hrGroupList.add(hrGroup);
                       }
                tx.commit();
                session.close();
                return hrGroupList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
