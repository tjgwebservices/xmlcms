package com.tjgwebservices.tjgxmlcms.dbo.article;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.article.Configuration;
import com.tjgwebservices.tjgxmlcms.model.article.Social;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SocialDBO extends DatabaseObject{

    public static void saveSocial(Social social){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(social);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLSocial(Social social) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Social(postname, content, reviewed, published) VALUES(?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,social.getPostname());
                pstmt.setString(2,social.getContent());
                pstmt.setInt(3,social.getReviewed());
                pstmt.setString(4,social.getPublished());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateSocial(Social social) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Social SET postname = ?, content = ?, reviewed = ?, published =? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,social.getPostname());
                pstmt.setString(2,social.getContent());
                pstmt.setInt(3,social.getReviewed());
                pstmt.setString(4,social.getPublished());
                pstmt.setInt(5,social.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Social> loadSocials(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Social> socialList = new ArrayList<>();
            String sql = "SELECT id,postname,content,reviewed,published FROM Social;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Social social = new Social();
                           social.setId(rs.getInt("id"));
                           social.setPostname(rs.getString("postname"));
                           social.setContent(rs.getString("content"));
                           social.setReviewed(rs.getInt("reviewed"));
                           social.setPublished(rs.getString("published"));
                           socialList.add(social);
                       }
                tx.commit();
                session.close();
                return socialList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return new ArrayList<Social>();
    }
    
}
