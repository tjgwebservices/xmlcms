package com.tjgwebservices.tjgxmlcms.dbo.video;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.video.Video;
import com.tjgwebservices.tjgxmlcms.services.DecodedMultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

public class VideoDBO extends DatabaseObject{

    public static void saveSQLVideo(Video video) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            MultipartFile lp = video.getVideoContent();
            try {
                conn = DriverManager.getConnection(connectionURL);
                //byte[] byteArrray = lp.getBytes();
                //InputStream targetStream = new ByteArrayInputStream(byteArrray);
                String sql = "INSERT INTO Video(videoName, videoPath) VALUES(?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,video.getVideoName());
                pstmt.setString(2, video.getVideoPath());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e.getMessage());
                    tx.rollback();
            }
    }

    public static List<Video> loadVideos(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Video> videoList = new ArrayList<>();
            String sql = "SELECT videoName,videoPath FROM Video;";
            try  {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);                
                       while(rs.next()){
                           Video video = new Video();
                           video.setVideoName(rs.getString("videoName"));
                           try {
                               byte[] fb = Files.readAllBytes(Paths.get(rs.getString("videoPath")));
                               //Binary<?> bd = (Binary<?>) bws.getClass().getConstructor().newInstance();
                               DecodedMultipartFile mpf = new DecodedMultipartFile(rs.getString("videoName"),fb);
                                video.setVideoContent(mpf);
                                videoList.add(video);
                           } catch (SecurityException ex) {
                               Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (IllegalArgumentException ex) {
                               Logger.getLogger(VideoDBO.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (IOException ex) {
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
