package com.tjgwebservices.tjgxmlcms.dbo.schools;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.Administrator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDBO extends DatabaseObject {

        private final static String TESTURL = "jdbc:sqlite:memory:articledb?cache=shared";

    public static void saveSQLAdministrator(Administrator administrator) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Administrator(administratorName, administratorGroupId) VALUES(?,?)";
            try (Connection conn = DriverManager.getConnection(TESTURL);
                ){
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,administrator.getAdministratorName());
                pstmt.setInt(2,administrator.getAdministratorGroupId());
                pstmt.executeUpdate();
                tx.commit();
                 session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Administrator> loadAdministrators(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Administrator> administratorList = new ArrayList<>();
            String sql = "SELECT id,administratorName,administratorGroupId FROM Administrator;";
            try (Connection conn = DriverManager.getConnection(TESTURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           Administrator administrator = new Administrator();
                           administrator.setId(rs.getInt("id"));
                           administrator.setAdministratorName(rs.getString("administratorName"));
                           administrator.setAdministratorGroupId(rs.getInt("administratorGroupId"));
                           administratorList.add(administrator);
                       }
                tx.commit();
                 session.close();
                return administratorList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
