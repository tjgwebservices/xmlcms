package com.tjgwebservices.tjgxmlcms.dbo.shop;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.shop.CartItem;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartItemDBO  extends DatabaseObject {

    public static void saveSQLCartItem(CartItem cartItem) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO CartItem(cartId,itemId) VALUES(?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,cartItem.getCartId());
                pstmt.setInt(2,cartItem.getItemId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<CartItem> loadCartItems(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<CartItem> cartItemList = new ArrayList<>();
            String sql = "SELECT id,cartId,itemId FROM CartItem;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                           CartItem cartItem = new CartItem();
                           cartItem.setId(rs.getInt("id"));
                           cartItem.setCartId(rs.getInt("cartId"));
                           cartItem.setItemId(rs.getInt("itemId"));
                           cartItemList.add(cartItem);
                       }
                tx.commit();
                session.close();
                return cartItemList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
