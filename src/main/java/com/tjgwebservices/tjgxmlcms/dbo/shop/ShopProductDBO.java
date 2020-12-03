package com.tjgwebservices.tjgxmlcms.dbo.shop;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopPaymentType;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopProduct;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShopProductDBO extends DatabaseObject {

    public static void saveSQLShopProduct(ShopProduct shopProduct) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO ShopProduct(description,price) VALUES(?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,shopProduct.getDescription());
                pstmt.setFloat(1,shopProduct.getPrice());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateShopProduct(ShopProduct shopProduct) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE ShopProduct SET description = ?, price = ? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,shopProduct.getDescription());
                pstmt.setInt(2,shopProduct.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }
    
    
    
    public static List<ShopProduct> loadShopProduct(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<ShopProduct> shopProductList = new ArrayList<>();
            String sql = "SELECT id,description,price FROM ShopProduct;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                           ShopProduct shopProduct = new ShopProduct();
                           shopProduct.setId(rs.getInt("id"));
                           shopProduct.setDescription(rs.getString("description"));
                           shopProduct.setPrice(rs.getFloat("price"));
                           shopProductList.add(shopProduct);
                       }
                tx.commit();
                session.close();
                return shopProductList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
