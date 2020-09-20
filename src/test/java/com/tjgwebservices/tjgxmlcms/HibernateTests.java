package com.tjgwebservices.tjgxmlcms;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.Article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class HibernateTests {
    private static SessionFactory sessionFactory = null;
    private static StandardServiceRegistry standardServiceRegistry = null;
    private String testurl = "jdbc:sqlite:memory:?cache=shared";
    private String testdb = "jdbc:sqlite:memory:articletestdb?cache=shared";

    @BeforeEach
    public void setup() throws Exception {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");        	
	StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
	sb.applySettings(cfg.getProperties());
	standardServiceRegistry = sb.build();           	
	sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);       
    }
    @Test
    public void testCreateDatabase() throws Exception {
        Session session = sessionFactory.openSession();
        String sql = "ATTACH DATABASE 'articletestdb.db' AS articletestdb;";
        Connection conn = DriverManager.getConnection(testurl);
        Statement stmt = conn.createStatement();
        boolean itr = stmt.execute(sql);
        Assertions.assertFalse(itr);
    }
    
    @Test
    public void testCreateTable() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS Article (\n"
                + " id integer PRIMARY KEY,\n"
                + " author text NOT NULL,\n"
                + " authorDate text NOT NULL,\n"
                + " title text NOT NULL,\n"
                + " description text NOT NULL,\n"
                + " content text NOT NULL);";
        Connection conn = DriverManager.getConnection(testdb);
        Statement stmt = conn.createStatement();
        Boolean itr = stmt.execute(sql);  
        Assertions.assertFalse(itr);
    }
    
    @Test
    public void testHibernateQuery() throws Exception {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        List<Article> articleList = new ArrayList<Article>();
        String sql = "SELECT id,author,authorDate,title,description,content FROM Article;";
        Connection conn = DriverManager.getConnection(testdb);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setId(rs.getInt("author"));
            article.setId(rs.getInt("authorDate"));
            article.setTitle(rs.getString("title"));
            article.setDescription(rs.getString("description"));
            article.setTitle(rs.getString("content"));
            articleList.add(article);
        }
        tr.commit();
        sessionFactory.close();
        Assertions.assertTrue(sessionFactory.isClosed());
    }

    
    @Test
    public void testDisplayTable() throws Exception {        
        Session session = sessionFactory.openSession();
        List<Article> articleList = new ArrayList<Article>();
        ResultSet rs = DriverManager
            .getConnection(testdb)
            .createStatement()
            .executeQuery("SELECT * FROM Article");
        
        while (rs.next()){
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setAuthor(rs.getString("author"));
            article.setAuthorDate(rs.getString("authorDate"));
            article.setTitle(rs.getString("title"));
            article.setDescription(rs.getString("description"));
            article.setContent(rs.getString("content"));
            articleList.add(article);
        }
        //Assertions.assertTrue(articleList.size()>0);
        
    }

    public void testInsertData() throws Exception {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Article article = new Article();
        article.setTitle("Test Title");
        article.setDescription("Test Description");
        String sql = "INSERT INTO Article(author,authorDate,title,description,content) VALUES(?,?,?,?,?)";
        Connection conn = DriverManager.getConnection(testdb);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,article.getAuthor());
        pstmt.setString(2,article.getAuthorDate());
        pstmt.setString(3,article.getTitle());
        pstmt.setString(4,article.getDescription());
        pstmt.setString(5,article.getContent());
        int itr = pstmt.executeUpdate();  
        Assertions.assertEquals(0, itr);
    }
    
    @Test
    public void testArticleData() throws Exception {
        HibernateAdmin.configureSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Article> articleList = new ArrayList<Article>();
        String sql = "SELECT id,author,authorDate,title,description,content FROM Article;";
        Connection conn = DriverManager.getConnection(testdb);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("author"));
            article.setDescription(rs.getString("authorDate"));
            article.setTitle(rs.getString("title"));
            article.setDescription(rs.getString("description"));
            article.setTitle(rs.getString("content"));
            articleList.add(article);
        }
        //Assertions.assertTrue(articleList.size()>0);        
    }
    
    
    private static List<Article> search(String queryString) {
        Session session = HibernateAdmin.getSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Article.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("title").matching(queryString).createQuery();
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Article.class);
        List<Article> articleList = fullTextQuery.list();
        fullTextSession.close();
        return articleList;
    }
        
        
    private static void indexDatabase() throws InterruptedException {
        Session session = HibernateAdmin.getSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
        fullTextSession.close();
    }
}
