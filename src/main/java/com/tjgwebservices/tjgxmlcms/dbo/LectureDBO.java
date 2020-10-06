package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.model.Lecture;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
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
import javax.websocket.Encoder.Binary;
import javax.websocket.server.ServerEndpoint;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LectureDBO {

    public static void saveSQLLecture(Lecture lecture) {
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            Binary lp = lecture.getLecturePoster();
            //Charset charset = StandardCharsets.US_ASCII;
            String text = lp.toString();
            byte[] byteArrray = text.getBytes(StandardCharsets.UTF_8);
            InputStream targetStream = new ByteArrayInputStream(byteArrray);
            String sql = "INSERT INTO Lecture(lectureName, lecturePoster) VALUES(?,?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,lecture.getLectureName());
                pstmt.setBinaryStream(2, targetStream);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Lecture> loadLectures(){
            Session session = HibernateAdmin.getSession();
            Transaction tx = session.beginTransaction();
            List<Lecture> lectureList = new ArrayList<>();
            String sql = "SELECT id,lectureName,lecturePoster FROM Lecture;";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                       while(rs.next()){
                           Lecture lecture = new Lecture();
                           lecture.setId(rs.getInt("id"));
                           lecture.setLectureName(rs.getString("administratorName"));
                           InputStream is = rs.getBinaryStream("lecturePoster");
                           BinaryWebSocketFrame bws = new BinaryWebSocketFrame(Unpooled.copiedBuffer(is.readAllBytes()));
                           final ServerEndpoint wse = bws.getClass().getAnnotation(ServerEndpoint.class);
                           try {
                               Binary<?> bd = (Binary<?>) bws.getClass().getConstructor().newInstance();
                                lecture.setLecturePoster(bd);
                                lectureList.add(lecture);
                           } catch (NoSuchMethodException ex) {
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (SecurityException ex) {
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (InstantiationException ex) {
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (IllegalAccessException ex) {
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (IllegalArgumentException ex) {
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (InvocationTargetException ex) {
                               Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }
                return lectureList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    tx.rollback();
            } catch (IOException ex) {
            Logger.getLogger(LectureDBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
