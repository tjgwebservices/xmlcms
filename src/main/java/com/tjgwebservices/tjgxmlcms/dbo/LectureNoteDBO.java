package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.LectureNote;
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

public class LectureNoteDBO {

    public static void saveSQLLectureNote(LectureNote lectureNote) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            String sql = "INSERT INTO LectureNote(noteInstructor,noteLecture,noteText) VALUES(?,?,?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,lectureNote.getNoteInstructor());
                pstmt.setString(2,lectureNote.getNoteLecture());
                pstmt.setString(3,lectureNote.getNoteText());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<LectureNote> loadLectureNotes(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<LectureNote> lectureNoteList = new ArrayList<>();
            String sql = "SELECT id,noteInstructor,noteLecture,noteText FROM Lecture;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           LectureNote lectureNote = new LectureNote();
                           lectureNote.setId(rs.getInt("id"));
                           lectureNote.setNoteInstructor(rs.getString("noteInstructor"));
                           lectureNote.setNoteLecture(rs.getString("noteLecture"));
                           lectureNote.setNoteText(rs.getString("noteText"));
                           lectureNoteList.add(lectureNote);
                       }
                return lectureNoteList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
