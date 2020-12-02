package com.tjgwebservices.tjgxmlcms.dbo.shop;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopOrderCart;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShopOrderCartDBO extends DatabaseObject {

    public static void saveSQLShopOrderCart(ShopOrderCart shopOrderCart) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO ShopOrderCart(orderId, cartId, orderPrice, orderTax) VALUES(?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,shopOrderCart.getOrderId());
                pstmt.setInt(2,shopOrderCart.getCartId());
                pstmt.setFloat(3,shopOrderCart.getOrderPrice());
                pstmt.setFloat(4,shopOrderCart.getOrderTax());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<ShopOrderCart> loadShopOrders(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<ShopOrderCart> shopOrderCartList = new ArrayList<>();
            String sql = "SELECT id,orderId,cartId,orderPrice,orderTax FROM ShopOrderCart;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                           ShopOrderCart shopOrderCart = new ShopOrderCart();
                           shopOrderCart.setId(rs.getInt("id"));
                           shopOrderCart.setOrderId(rs.getInt("orderId"));
                           shopOrderCart.setCartId(rs.getInt("cartId"));
                           shopOrderCart.setOrderPrice(rs.getFloat("orderPrice"));
                           shopOrderCart.setOrderTax(rs.getFloat("orderTax"));
                           shopOrderCartList.add(shopOrderCart);
                       }
                tx.commit();
                session.close();
                return shopOrderCartList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
