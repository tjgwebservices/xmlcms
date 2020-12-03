package com.tjgwebservices.tjgxmlcms.dbo.shop;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopOrderStatus;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShopOrderStatusDBO  extends DatabaseObject {

    public static void saveSQLShopOrderStatus(ShopOrderStatus shopOrderStatus) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO ShopOrderStatus(description) VALUES(?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,shopOrderStatus.getDescription());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateShopOrderStatus(ShopOrderStatus shopOrderStatus) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE ShopOrderStatus SET description = ? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,shopOrderStatus.getDescription());
                pstmt.setInt(2,shopOrderStatus.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }
    
    
    public static List<ShopOrderStatus> loadShopOrderStatuses(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<ShopOrderStatus> shopOrderStatusList = new ArrayList<>();
            String sql = "SELECT id,description FROM ShopOrderStatus;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                           ShopOrderStatus shopOrderStatus = new ShopOrderStatus();
                           shopOrderStatus.setId(rs.getInt("id"));
                           shopOrderStatus.setDescription(rs.getString("description"));
                           shopOrderStatusList.add(shopOrderStatus);
                       }
                tx.commit();
                session.close();
                return shopOrderStatusList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
