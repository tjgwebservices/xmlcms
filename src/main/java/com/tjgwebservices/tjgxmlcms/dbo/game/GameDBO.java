package com.tjgwebservices.tjgxmlcms.dbo.game;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import com.tjgwebservices.tjgxmlcms.model.game.Game;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameDBO extends DatabaseObject{

    public static void saveSQLGame(Game game) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Game(title, highScore, created, lastUpdated) VALUES(?,?,?,?)";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,game.getTitle());
                pstmt.setInt(2,game.getHighScore());
                pstmt.setString(3,game.getCreated());
                pstmt.setString(4,game.getLastUpdated());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Game> loadGames(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Game> gameList = new ArrayList<>();
            String sql = "SELECT id,title, highScore, created, lastUpdated FROM Game;";
            try {
                conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Game game = new Game();
                           game.setId(rs.getInt("id"));
                           game.setTitle(rs.getString("title"));
                           game.setHighScore(rs.getInt("highScore"));
                           game.setCreated(rs.getString("created"));
                           game.setLastUpdated(rs.getString("lastUpdated"));
                           gameList.add(game);
                       }
                tx.commit();
                session.close();
                return gameList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }

    public static void updateGame(Game game) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Game SET title = ?, highScore = ?, created = ?, lastUpdated =? WHERE id = ?";
            try {
                conn = DriverManager.getConnection(connectionURL);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,game.getTitle());
                pstmt.setInt(2,game.getHighScore());
                pstmt.setTimestamp(3,convertTimeStamp(game.getCreated()));
                pstmt.setTimestamp(4,convertTimeStamp(game.getLastUpdated()));
                pstmt.setInt(5,game.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }
    
    public static Timestamp convertTimeStamp(String timestampAsString){
        String pattern = "MMM dd, yyyy HH:mm:ss.SS";
        //String timestampAsString = "Nov 12, 2018 13:02:56.12345678";
        Timestamp timestamp;
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timestampAsString));
        timestamp = Timestamp.valueOf(localDateTime);
        } catch (DateTimeParseException e){
            Date date = new Date();
            timestamp = new Timestamp(date.getTime());
        }
        return timestamp;
        
        
        
    }
    
}
