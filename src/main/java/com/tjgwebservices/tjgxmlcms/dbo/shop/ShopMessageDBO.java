package com.tjgwebservices.tjgxmlcms.dbo.shop;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopMessage;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShopMessageDBO extends DatabaseObject {

    public static void saveSQLShopMessage(ShopMessage shopMessage) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO ShopMessage(message, email, body, createdTime) VALUES(?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,shopMessage.getMessage());
                pstmt.setString(2,shopMessage.getEmail());
                pstmt.setString(3,shopMessage.getBody());
                pstmt.setString(4,shopMessage.getCreatedTime());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<ShopMessage> loadShopMessages(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<ShopMessage> shopMessageList = new ArrayList<>();
            String sql = "SELECT id,message,email,body,createdTime FROM ShopMessage;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                           ShopMessage shopMessage = new ShopMessage();
                           shopMessage.setId(rs.getInt("id"));
                           shopMessage.setMessage(rs.getString("message"));
                           shopMessage.setEmail(rs.getString("email"));
                           shopMessage.setBody(rs.getString("body"));
                           shopMessageList.add(shopMessage);
                       }
                tx.commit();
                session.close();
                return shopMessageList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
