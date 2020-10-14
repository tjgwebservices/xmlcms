package com.tjgwebservices.tjgxmlcms.dbo.hr;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.hr.HrClient;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HrClientDBO extends DatabaseObject {

    public static void saveSQLHrClient(HrClient hrClient) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO HrClient(clientFirstName,clientLastName,clientSpecialty,clientContact,hrGroupId) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,hrClient.getClientFirstName());
                pstmt.setString(2,hrClient.getClientLastName());
                pstmt.setString(3,hrClient.getClientSpecialty());
                pstmt.setString(4,hrClient.getClientContact());
                pstmt.setInt(5,hrClient.getHrGroupId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateHrClient(HrClient hrClient) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE HrClient SET clientFirstName=?,clientLastName=?,clientSpecialty=?,clientContact=?,hrGroupId=? WHERE id = ?";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,hrClient.getClientFirstName());
                pstmt.setString(2,hrClient.getClientLastName());
                pstmt.setString(3,hrClient.getClientSpecialty());
                pstmt.setString(4,hrClient.getClientContact());
                pstmt.setInt(5,hrClient.getHrGroupId());
                pstmt.setInt(6,hrClient.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<HrClient> loadHrClients(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<HrClient> hrClientList = new ArrayList<>();
            String sql = "SELECT id,clientFirstName,clientLastName,clientSpecialty,clientContact,hrGroupId FROM HrClient;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           HrClient hrClient = new HrClient();
                           hrClient.setId(rs.getInt("id"));
                           hrClient.setClientFirstName(rs.getString("clientFirstName"));
                           hrClient.setClientLastName(rs.getString("clientLastName"));
                           hrClient.setClientSpecialty(rs.getString("clientSpecialty"));
                           hrClient.setClientContact(rs.getString("clientContact"));
                           hrClient.setHrGroupId(rs.getInt("hrGroupId"));
                           hrClientList.add(hrClient);
                       }
                tx.commit();
                session.close();
                return hrClientList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
