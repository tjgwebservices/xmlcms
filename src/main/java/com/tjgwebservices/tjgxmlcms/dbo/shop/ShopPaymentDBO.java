package com.tjgwebservices.tjgxmlcms.dbo.shop;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopPayment;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShopPaymentDBO  extends DatabaseObject {

    public static void saveSQLShopPayment(ShopPayment shopPayment) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO ShopPayment(paymentType, ccType, ccOwner, "
                    + "ccNumber, ccExpires, lastModified, datePurchased, orderStatus, "
                    + "orderDateFinished, currency, currencyValue) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,shopPayment.getPaymentType());
                pstmt.setString(2,shopPayment.getCcType());
                pstmt.setString(3,shopPayment.getCcOwner());
                pstmt.setString(4,shopPayment.getCcNumber());
                pstmt.setString(5,shopPayment.getCcExpires());
                pstmt.setString(6,shopPayment.getLastModified());
                pstmt.setString(7,shopPayment.getDatePurchased());
                pstmt.setInt(8,shopPayment.getOrderStatus());
                pstmt.setString(9,shopPayment.getOrderDateFinished());
                pstmt.setString(10,shopPayment.getCurrency());
                pstmt.setFloat(11,shopPayment.getCurrencyValue());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<ShopPayment> loadShopPayments(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<ShopPayment> shopPaymentList = new ArrayList<>();
            String sql = "SELECT id,paymentType, ccType, ccOwner, "
                    + "ccNumber, ccExpires, lastModified, datePurchased, orderStatus, "
                    + "orderDateFinished, currency, currencyValue FROM ShopPayment;";
            try  {
                        conn = DriverManager.getConnection(connectionURL);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                           ShopPayment shopPayment = new ShopPayment();
                           shopPayment.setId(rs.getInt("id"));
                           shopPayment.setPaymentType(rs.getInt("paymentType"));
                           shopPayment.setCcType(rs.getString("ccType"));
                           shopPayment.setCcOwner(rs.getString("ccOwner"));
                           shopPayment.setCcNumber(rs.getString("ccNumber"));
                           shopPayment.setCcExpires(rs.getString("ccExpires"));
                           shopPayment.setLastModified(rs.getString("lastModified"));
                           shopPayment.setDatePurchased(rs.getString("datePurchased"));
                           shopPayment.setOrderStatus(rs.getInt("orderStatus"));
                           shopPayment.setOrderDateFinished(rs.getString("orderDateFinished"));
                           shopPayment.setCurrency(rs.getString("currency"));
                           shopPayment.setCurrencyValue(rs.getFloat("currencyValue"));
                           shopPaymentList.add(shopPayment);
                       }
                tx.commit();
                session.close();
                return shopPaymentList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
