package com.tjgwebservices.tjgxmlcms.dbo.video;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.video.Artist;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistDBO extends DatabaseObject{

    public static void saveSQLArtist(Artist artist) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Artist(artistName) VALUES(?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql); 
                pstmt.setString(1,artist.getArtistName());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Artist> loadArtists(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Artist> artistList = new ArrayList<>();
            String sql = "SELECT id,artistName FROM Artist;";
            try  {
                        conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);                
                       while(rs.next()){
                           Artist artist = new Artist();
                           artist.setId(rs.getInt("id"));
                           artist.setArtistName(rs.getString("artistName"));
                           artistList.add(artist);
                       }
                tx.commit();
                session.close();
                return artistList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }

    
}
