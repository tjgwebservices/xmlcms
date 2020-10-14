package com.tjgwebservices.tjgxmlcms.dbo.schools;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.LectureNote;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LectureNoteDBO extends DatabaseObject{

    public static void saveSQLLectureNote(LectureNote lectureNote) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO LectureNote(noteInstructor,noteLecture,noteText) VALUES(?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,lectureNote.getNoteInstructor());
                pstmt.setString(2,lectureNote.getNoteLecture());
                pstmt.setString(3,lectureNote.getNoteText());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<LectureNote> loadLectureNotes(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<LectureNote> lectureNoteList = new ArrayList<>();
            String sql = "SELECT id,noteInstructor,noteLecture,noteText FROM LectureNote;";
            try  {
                        conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);                
                       while(rs.next()){
                           LectureNote lectureNote = new LectureNote();
                           lectureNote.setId(rs.getInt("id"));
                           lectureNote.setNoteInstructor(rs.getString("noteInstructor"));
                           lectureNote.setNoteLecture(rs.getString("noteLecture"));
                           lectureNote.setNoteText(rs.getString("noteText"));
                           lectureNoteList.add(lectureNote);
                       }
                tx.commit();
                session.close();
                return lectureNoteList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
