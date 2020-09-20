package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.Article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ArticleDBO {

    public static void saveArticle(Article article){
        try {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            session.save(article);
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLArticle(Article article) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO Article(author, authorDate, title, description, content) VALUES(?,?,?,?,?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,article.getAuthor());
                pstmt.setString(2,article.getAuthorDate());
                pstmt.setString(3,article.getTitle());
                pstmt.setString(4,article.getDescription());
                pstmt.setString(5,article.getContent());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Article> loadArticles(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<Article> articleList = new ArrayList<Article>();
            String sql = "SELECT id,author,authorDate,title,description,content FROM Article;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           Article article = new Article();
                           article.setId(rs.getInt("id"));
                           article.setAuthor(rs.getString("author"));
                           article.setAuthorDate(rs.getString("authorDate"));
                           article.setTitle(rs.getString("title"));
                           article.setDescription(rs.getString("description"));
                           article.setContent(rs.getString("content"));
                           articleList.add(article);
                       }
                return articleList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return createDummyData();
    }

    public static List<Article> createDummyData(){
        List<Article> articleList = new ArrayList<Article>();
        articleList.add(new Article("John Doe","September 1, 2020", "Article Title 1", "Article Description 1", "Content"));
        articleList.add(new Article("John Doe","September 1, 2020", "Article Title 1", "Article Description 2", "Content"));
        return articleList;        
    }
    
}
