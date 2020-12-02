package com.tjgwebservices.tjgxmlcms.dbo.shop;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.shop.CartStatus;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartStatusDBO extends DatabaseObject {

    public static void saveSQLCartStatus(CartStatus cartStatus) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO CartStatus(description) VALUES(?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,cartStatus.getDescription());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<CartStatus> loadCartStatuses(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<CartStatus> cartStatusList = new ArrayList<>();
            String sql = "SELECT id,description FROM CartStatus;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                           CartStatus cartStatus = new CartStatus();
                           cartStatus.setId(rs.getInt("id"));
                           cartStatus.setDescription(rs.getString("description"));
                           cartStatusList.add(cartStatus);
                       }
                tx.commit();
                session.close();
                return cartStatusList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
