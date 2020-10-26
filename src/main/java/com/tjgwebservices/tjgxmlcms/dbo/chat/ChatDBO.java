package com.tjgwebservices.tjgxmlcms.dbo.chat;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.chat.Chat;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChatDBO extends DatabaseObject{

    public static void saveChat(Chat chat){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(chat);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void saveSQLChat(Chat chat) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Chat(userIdFrom, userIdTo, dateTime, priority, subject, message) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,chat.getUserIdFrom());
                pstmt.setInt(2,chat.getUserIdTo());
                pstmt.setString(3,chat.getDateTime());
                pstmt.setString(4,chat.getSubject());
                pstmt.setString(5,chat.getMessage());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Chat> loadChats(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Chat> chatList = new ArrayList<>();
            String sql = "SELECT id,userIdFrom,userIdTo,dateTime,priority,subject,message FROM Chat;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Chat chat = new Chat();
                           chat.setId(rs.getInt("id"));
                           chat.setUserIdFrom(rs.getInt("userIdFrom"));
                           chat.setUserIdTo(rs.getInt("userIdTo"));
                           chat.setDateTime(rs.getString("dateTime"));
                           chat.setPriority(rs.getInt("priority"));
                           chat.setSubject(rs.getString("subject"));
                           chat.setMessage(rs.getString("message"));
                           chatList.add(chat);
                       }
                tx.commit();
                session.close();
                return chatList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
