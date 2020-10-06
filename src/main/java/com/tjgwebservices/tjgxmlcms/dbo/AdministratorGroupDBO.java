package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.Administrator;
import com.tjgwebservices.tjgxmlcms.model.AdministratorGroup;
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

public class AdministratorGroupDBO {

    public static void saveSQLAdministratorGroup(AdministratorGroup administratorGroup) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO AdministratorGroup(groupName) VALUES(?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,administratorGroup.getGroupName());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<AdministratorGroup> loadAdministrators(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<AdministratorGroup> administratorGroupList = new ArrayList<>();
            String sql = "SELECT id,groupName FROM AdministratorGroup;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           AdministratorGroup administratorGroup = new AdministratorGroup();
                           administratorGroup.setId(rs.getInt("id"));
                           administratorGroup.setGroupName(rs.getString("groupName"));
                           administratorGroupList.add(administratorGroup);
                       }
                return administratorGroupList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
