package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.SerialJournal;
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

public class JournalDBO {
    public static void saveJournal(SerialJournal journal) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO Journal(journalName) VALUES(?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,journal.getJournalName());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<SerialJournal> loadJournals(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<SerialJournal> journalList = new ArrayList<SerialJournal>();
            String sql = "SELECT journalName FROM Journal;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           SerialJournal journal = new SerialJournal();
                           journal.setId(rs.getInt("id"));
                           journal.setJournal(rs.getString("journalName"));
                           journalList.add(journal);
                       }
                return journalList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
            return journalList;
    }   
    
}
