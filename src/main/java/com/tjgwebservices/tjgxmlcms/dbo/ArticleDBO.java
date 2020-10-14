package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.Article;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDBO extends DatabaseObject{

    public static void saveArticle(Article article){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(article);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLArticle(Article article) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Article(author, authorDate, title, description, content) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,article.getAuthor());
                pstmt.setString(2,article.getAuthorDate());
                pstmt.setString(3,article.getTitle());
                pstmt.setString(4,article.getDescription());
                pstmt.setString(5,article.getContent());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateArticle(Article article) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Article SET author = ?, authorDate = ?, title = ?, description=?, content=? WHERE id = ?";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,article.getAuthor());
                pstmt.setString(2,article.getAuthorDate());
                pstmt.setString(3,article.getTitle());
                pstmt.setString(4,article.getDescription());
                pstmt.setString(5,article.getContent());
                pstmt.setInt(6,article.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Article> loadArticles(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Article> articleList = new ArrayList<>();
            String sql = "SELECT id,author,authorDate,title,description,content FROM Article;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
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
                tx.commit();
                session.close();
                return articleList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return createDummyData();
    }

    public static List<Article> createDummyData(){
        List<Article> articleList = new ArrayList<>();
        articleList.add(new Article("John Doe","September 1, 2020", "Article Title 1", "Article Description 1", "Content"));
        articleList.add(new Article("John Doe","September 1, 2020", "Article Title 1", "Article Description 2", "Content"));
        return articleList;        
    }
    
}
