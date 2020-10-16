package com.tjgwebservices.tjgxmlcms.dbo.video;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.dbo.schools.LectureDBO;
import com.tjgwebservices.tjgxmlcms.model.Lecture;
import com.tjgwebservices.tjgxmlcms.model.Video;
import com.tjgwebservices.tjgxmlcms.services.DecodedMultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

public class VideoDBO {

    public static void saveSQLVideo(Video video) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            MultipartFile lp = video.getVideoContent();
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                byte[] byteArrray = lp.getBytes();
                InputStream targetStream = new ByteArrayInputStream(byteArrray);
                String sql = "INSERT INTO Video(videoName, videoContent) VALUES(?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,video.getVideoName());
                pstmt.setBinaryStream(2, targetStream);
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e.getMessage());
                    tx.rollback();
            } catch (IOException e) {
                    Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, e);
            }
    }

    public static List<Video> loadVideos(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Video> videoList = new ArrayList<>();
            String sql = "SELECT videoName,videoContent FROM Video;";
            try  {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);                
                       while(rs.next()){
                           Video video = new Video();
                           video.setVideoName(rs.getString("videoName"));
                           InputStream is = rs.getBinaryStream("videoContent");
                           //BinaryWebSocketFrame bws = new BinaryWebSocketFrame(Unpooled.copiedBuffer(is.readAllBytes()));
                           //final ServerEndpoint wse = bws.getClass().getAnnotation(ServerEndpoint.class);
                           try {
                               //Binary<?> bd = (Binary<?>) bws.getClass().getConstructor().newInstance();
                               DecodedMultipartFile mpf = new DecodedMultipartFile(is);
                                video.setVideoContent(mpf);
                                videoList.add(video);
                           } catch (SecurityException ex) {
                               Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, ex);
                               Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (IllegalArgumentException ex) {
                               Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }
                tx.commit();
                session.close();
                return videoList;
            } catch (SQLException e) {
                    Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e.getMessage());
                    tx.rollback();
        }
        return null;
    }
    
}
