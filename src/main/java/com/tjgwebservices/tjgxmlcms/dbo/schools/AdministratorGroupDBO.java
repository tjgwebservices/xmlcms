package com.tjgwebservices.tjgxmlcms.dbo.schools;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.school.AdministratorGroup;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministratorGroupDBO extends DatabaseObject{

    public static void saveSQLAdministratorGroup(AdministratorGroup administratorGroup) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO AdministratorGroup(groupName) VALUES(?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,administratorGroup.getGroupName());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<AdministratorGroup> loadAdministratorGroups(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<AdministratorGroup> administratorGroupList = new ArrayList<>();
            String sql = "SELECT id,groupName FROM AdministratorGroup;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           AdministratorGroup administratorGroup = new AdministratorGroup();
                           administratorGroup.setId(rs.getInt("id"));
                           administratorGroup.setGroupName(rs.getString("groupName"));
                           administratorGroupList.add(administratorGroup);
                       }
                tx.commit();
                session.close();
                return administratorGroupList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
