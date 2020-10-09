package com.tjgwebservices.tjgxmlcms.dbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseObject {

    public static String connectionURL;
    public static Session session;
    public static Transaction tx;
    public static PreparedStatement pstmt;
    public static Connection conn;
    
    private final static String TESTURL = "jdbc:sqlite:memory:articledb?cache=shared";

                
                    
    public DatabaseObject(){
        this.connectionURL = TESTURL;    
    }
    
    public DatabaseObject(String connectionURL){
        this.connectionURL = connectionURL;    
    
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTx() {
        return tx;
    }

    public void setTx(Transaction tx) {
        this.tx = tx;
    }

    public PreparedStatement getPstmt() {
        return pstmt;
    }

    public void setPstmt(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }

}
