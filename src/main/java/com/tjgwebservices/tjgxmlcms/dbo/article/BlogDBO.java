package com.tjgwebservices.tjgxmlcms.dbo.article;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.article.Blog;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BlogDBO extends DatabaseObject{

    public static void saveBlog(Blog blog){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(blog);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLBlog(Blog blog) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Blog(author, authorDate, title, description, content) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,blog.getAuthor());
                pstmt.setString(2,blog.getAuthorDate());
                pstmt.setString(3,blog.getTitle());
                pstmt.setString(4,blog.getDescription());
                pstmt.setString(5,blog.getContent());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateBlog(Blog blog) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Blog SET author = ?, authorDate = ?, title = ?, description=?, content=? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,blog.getAuthor());
                pstmt.setString(2,blog.getAuthorDate());
                pstmt.setString(3,blog.getTitle());
                pstmt.setString(4,blog.getDescription());
                pstmt.setString(5,blog.getContent());
                pstmt.setInt(6,blog.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Blog> loadBlogs(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Blog> blogList = new ArrayList<>();
            String sql = "SELECT id,author,authorDate,title,description,content FROM Blog;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Blog blog = new Blog();
                           blog.setId(rs.getInt("id"));
                           blog.setAuthor(rs.getString("author"));
                           blog.setAuthorDate(rs.getString("authorDate"));
                           blog.setTitle(rs.getString("title"));
                           blog.setDescription(rs.getString("description"));
                           blog.setContent(rs.getString("content"));
                           blogList.add(blog);
                       }
                tx.commit();
                session.close();
                return blogList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return new ArrayList();
    }
    
}
