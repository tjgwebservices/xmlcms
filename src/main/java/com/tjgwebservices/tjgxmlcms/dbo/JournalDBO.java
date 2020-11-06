package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.subscription.SerialJournal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JournalDBO extends DatabaseObject {
    public static void saveJournal(SerialJournal journal) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Journal(journalName) VALUES(?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);             
                pstmt.setString(1,journal.getJournalName());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<SerialJournal> loadJournals(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<SerialJournal> journalList = new ArrayList<SerialJournal>();
            String sql = "SELECT journalName FROM Journal;";
            try (Connection conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           SerialJournal journal = new SerialJournal();
                           journal.setId(rs.getInt("id"));
                           journal.setJournal(rs.getString("journalName"));
                           journalList.add(journal);
                       }
                tx.commit();
                session.close();
                return journalList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
            return journalList;
    }   
    
}
