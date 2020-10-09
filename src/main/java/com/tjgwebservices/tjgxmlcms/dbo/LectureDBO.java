package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.Lecture;
import com.tjgwebservices.tjgxmlcms.services.DecodedMultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.multipart.MultipartFile;

public class LectureDBO {

    public static void saveSQLLecture(Lecture lecture) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            MultipartFile lp = lecture.getLecturePoster();
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared")) {
                byte[] byteArrray = lp.getBytes();
                InputStream targetStream = new ByteArrayInputStream(byteArrray);
                String sql = "INSERT INTO Lecture(lectureName, lecturePoster) VALUES(?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,lecture.getLectureName());
                pstmt.setBinaryStream(2, targetStream);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e.getMessage());
                    tx.rollback();
            } catch (IOException e) {
                    Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, e);
            }
    }

    public static List<Lecture> loadLectures(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<Lecture> lectureList = new ArrayList<>();
            String sql = "SELECT lectureName,lecturePoster FROM Lecture;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           Lecture lecture = new Lecture();
                           lecture.setLectureName(rs.getString("administratorName"));
                           InputStream is = rs.getBinaryStream("lecturePoster");
                           //BinaryWebSocketFrame bws = new BinaryWebSocketFrame(Unpooled.copiedBuffer(is.readAllBytes()));
                           //final ServerEndpoint wse = bws.getClass().getAnnotation(ServerEndpoint.class);
                           try {
                               //Binary<?> bd = (Binary<?>) bws.getClass().getConstructor().newInstance();
                               DecodedMultipartFile mpf = new DecodedMultipartFile(is);
                                lecture.setLecturePoster(mpf);
                                lectureList.add(lecture);
                           } catch (SecurityException ex) {
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (IllegalArgumentException ex) {
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }
                return lectureList;
            } catch (SQLException e) {
                    Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e.getMessage());
                    tx.rollback();
        }
        return null;
    }
    
}
