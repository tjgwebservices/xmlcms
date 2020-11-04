package com.tjgwebservices.tjgxmlcms.dbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseObject {

    protected final static String connectionURL = "jdbc:sqlite:memory:articledb?cache=shared";
    protected static Session session;
    protected static Transaction tx;
    protected static PreparedStatement pstmt;
    protected static Connection conn;
    
    private static String TESTURL;

                
                    

    public String getConnectionURL() {
        return connectionURL;
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
