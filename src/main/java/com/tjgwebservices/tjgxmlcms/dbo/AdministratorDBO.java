package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.Administrator;
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
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Administrator> loadAdministrators(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
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
                return administratorList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
