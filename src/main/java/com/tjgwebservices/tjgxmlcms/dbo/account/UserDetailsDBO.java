package com.tjgwebservices.tjgxmlcms.dbo.account;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.account.UserDetails;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsDBO  extends DatabaseObject{

    public static void saveSQLUserDetails(UserDetails userDetails) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO AccountUserDetails (content1, content2, content3, accountUserId) "
                    + "VALUES(?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,userDetails.getContent1());
                pstmt.setString(2,userDetails.getContent2());
                pstmt.setString(3,userDetails.getContent3());
                pstmt.setInt(4,userDetails.getAccountUserId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateUserDetails(UserDetails userDetails) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE AccountUserDetails SET content1 =?, content2 =?, "
                    + "content3 =?, accountUserId =? WHERE id =?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,userDetails.getContent1());
                pstmt.setString(2,userDetails.getContent2());
                pstmt.setString(3,userDetails.getContent3());
                pstmt.setInt(4,userDetails.getAccountUserId());
                pstmt.setInt(5,userDetails.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<UserDetails> loadUserDetails(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<UserDetails> userDetailsList = new ArrayList<>();
            String sql = "SELECT id,content1,content2,content3,accountUserId "
                    + "FROM AccountUserDetails;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           UserDetails userDetails = new UserDetails();
                           userDetails.setId(rs.getInt("id"));
                           userDetails.setContent1(rs.getString("content1"));
                           userDetails.setContent2(rs.getString("content2"));
                           userDetails.setContent3(rs.getString("content3"));
                           userDetails.setAccountUserId(rs.getInt("accountUserId"));
                           userDetailsList.add(userDetails);
                       }
                tx.commit();
                session.close();
                return userDetailsList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return userDetailsList;
    }
    
}
