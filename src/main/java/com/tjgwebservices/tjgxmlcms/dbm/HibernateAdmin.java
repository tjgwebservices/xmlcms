package com.tjgwebservices.tjgxmlcms.dbm;

import com.tjgwebservices.tjgxmlcms.model.Article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
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

public class HibernateAdmin {
    private static SessionFactory sessionFactory = null;
    private static StandardServiceRegistry standardServiceRegistry = null;
    private static Session session = null;
    private static Transaction tx = null;

    public static void saveArticle(Article article){
        HibernateAdmin.configureSessionFactory();
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(article);
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLArticle(Article article) {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Article(title,description) VALUES(?,?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,article.getTitle());
                pstmt.setString(2,article.getDescription());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        
    }
    
    public static List<Article> loadArticles(){
        HibernateAdmin.configureSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            List<Article> articleList = new ArrayList<Article>();
            String sql = "SELECT id,title,description FROM Article;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           Article article = new Article();
                           article.setId(rs.getInt("id"));
                           article.setTitle(rs.getString("title"));
                           article.setDescription(rs.getString("description"));
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
        articleList.add(new Article("Article Title 1", "Article Description 1"));
        articleList.add(new Article("Article Title 1", "Article Description 2"));
        return articleList;        
    }
        
    public static SessionFactory configureSessionFactory() throws HibernateException {


        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");        	
	StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
	sb.applySettings(cfg.getProperties());
	standardServiceRegistry = sb.build();           	
	sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
        Session session = sessionFactory.openSession();
        String sql = "ATTACH DATABASE 'articledb.db' AS articledb;";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:?cache=shared");
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sql = "CREATE TABLE IF NOT EXISTS Article (\n"
                + " id integer PRIMARY KEY,\n"
                + " title text NOT NULL,\n"
                + " description text);";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return sessionFactory;
    }

    private static void indexDatabase() throws InterruptedException {
        Session session = HibernateAdmin.getSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
        fullTextSession.close();
    }

    private static List<Article> search(String queryString) {
        session = HibernateAdmin.getSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Article.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("name").matching(queryString).createQuery();
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Article.class);
        List<Article> articleList = fullTextQuery.list();
        fullTextSession.close();
        return articleList;
    }

    private static void displayArticleTableData() {
        try {
            session = HibernateAdmin.getSession();
            List<Article> articleList = session.createQuery("from Article").list();
            for (Article article : articleList) {
                System.out.println(article.getTitle());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            if(session != null) {
                session.close();
            }
        }
    }


    static {
        configureSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }  

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateAdmin.sessionFactory = sessionFactory;
    }

    public static StandardServiceRegistry getStandardServiceRegistry() {
        return standardServiceRegistry;
    }

    public static void setStandardServiceRegistry(StandardServiceRegistry standardServiceRegistry) {
        HibernateAdmin.standardServiceRegistry = standardServiceRegistry;
    }
}
