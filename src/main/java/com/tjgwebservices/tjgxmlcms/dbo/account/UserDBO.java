package com.tjgwebservices.tjgxmlcms.dbo.account;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.account.User;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDBO extends DatabaseObject{

    public static void saveSQLUser(User user) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO AccountUser(username, firstName, lastName, email, phoneNumber,"
                    + "address1, address2, city, statecode, zipcode, businessName, websiteName) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,user.getUsername());
                pstmt.setString(2,user.getFirstName());
                pstmt.setString(3,user.getLastName());
                pstmt.setString(4,user.getEmail());
                pstmt.setString(5,user.getPhoneNumber());
                pstmt.setString(6,user.getAddress1());
                pstmt.setString(7,user.getAddress2());
                pstmt.setString(8,user.getCity());
                pstmt.setString(9,user.getStatecode());
                pstmt.setString(10,user.getZipcode());
                pstmt.setString(11,user.getBusinessName());
                pstmt.setString(12,user.getWebsiteName());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateUser(User user) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE AccountUser SET username =?, firstName =?, lastName =?, email =?, phoneNumber =?, "
                    + "address1 =?, address2 =?, city =?, statecode =?, zipcode =?, businessName =?, "
                    + "websiteName =? WHERE id =?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,user.getUsername());
                pstmt.setString(2,user.getFirstName());
                pstmt.setString(3,user.getLastName());
                pstmt.setString(4,user.getEmail());
                pstmt.setString(5,user.getPhoneNumber());
                pstmt.setString(6,user.getAddress1());
                pstmt.setString(7,user.getAddress2());
                pstmt.setString(8,user.getCity());
                pstmt.setString(9,user.getStatecode());
                pstmt.setString(10,user.getZipcode());
                pstmt.setString(11,user.getBusinessName());
                pstmt.setString(12,user.getWebsiteName());
                pstmt.setInt(13,user.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<User> loadUsers(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<User> userList = new ArrayList<>();
            String sql = "SELECT id,username,firstName,lastName,email,phoneNumber,"
                    + "address1,address2,city,statecode,zipcode,businessName,"
                    + "websiteName FROM AccountUser;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           User user = new User();
                           user.setId(rs.getInt("id"));
                           user.setUsername(rs.getString("username"));
                           user.setFirstName(rs.getString("firstName"));
                           user.setLastName(rs.getString("lastName"));
                           user.setEmail(rs.getString("email"));
                           user.setPhoneNumber(rs.getString("phoneNumber"));
                           user.setAddress1(rs.getString("address1"));
                           user.setAddress2(rs.getString("address2"));
                           user.setCity(rs.getString("city"));
                           user.setStatecode(rs.getString("statecode"));
                           user.setZipcode(rs.getString("zipcode"));
                           user.setBusinessName(rs.getString("businessName"));
                           user.setWebsiteName(rs.getString("websiteName"));
                           userList.add(user);
                       }
                tx.commit();
                session.close();
                return userList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return userList;
    }

    public static boolean deleteUser(User user) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "DELETE FROM AccountUser WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,user.getId());
                tx.commit();
                session.close();
                return true;
            } catch (SQLException e){
                System.out.println(e.getMessage());
                tx.rollback();                
                return false;
            }
    }

    
}
