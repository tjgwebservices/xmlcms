package com.tjgwebservices.tjgxmlcms.dbo.review;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.review.Review;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDBO extends DatabaseObject{

    public static void saveReview(Review review){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(review);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLReview(Review review) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Review(author, authorDate, title, description, content) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,review.getAuthor());
                pstmt.setString(2,review.getAuthorDate());
                pstmt.setString(3,review.getTitle());
                pstmt.setString(4,review.getDescription());
                pstmt.setString(5,review.getContent());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateReview(Review review) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Review SET author = ?, authorDate = ?, title = ?, description=?, content=? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,review.getAuthor());
                pstmt.setString(2,review.getAuthorDate());
                pstmt.setString(3,review.getTitle());
                pstmt.setString(4,review.getDescription());
                pstmt.setString(5,review.getContent());
                pstmt.setInt(6,review.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Review> loadReviews(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Review> reviewList = new ArrayList<>();
            String sql = "SELECT id,author,authorDate,title,description,content FROM Review;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Review review = new Review();
                           review.setId(rs.getInt("id"));
                           review.setAuthor(rs.getString("author"));
                           review.setAuthorDate(rs.getString("authorDate"));
                           review.setTitle(rs.getString("title"));
                           review.setDescription(rs.getString("description"));
                           review.setContent(rs.getString("content"));
                           reviewList.add(review);
                       }
                tx.commit();
                session.close();
                return reviewList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return createDummyData();
    }

    public static List<Review> createDummyData(){
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review("John Doe","September 1, 2020", "Review Title 1", "Review Description 1", "Content"));
        reviewList.add(new Review("John Doe","September 1, 2020", "Review Title 1", "Review Description 2", "Content"));
        return reviewList;        
    }
    
}
