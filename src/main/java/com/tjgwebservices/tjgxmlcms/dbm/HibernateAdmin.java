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
                + " author text NOT NULL,\n"
                + " authorDate text NOT NULL,\n"
                + " title text NOT NULL,\n"
                + " description text NOT NULL,\n"
                + " content text NOT NULL);";
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
