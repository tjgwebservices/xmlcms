package com.tjgwebservices.tjgxmlcms.dbo.research;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.research.Topic;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TopicDBO extends DatabaseObject{

    public static void saveSQLTopic(Topic topic) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Topic(topicName,topicSubject,topicDescription,researcherId) VALUES(?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,topic.getTopicName());
                pstmt.setString(2,topic.getTopicSubject());
                pstmt.setString(3,topic.getTopicDescription());
                pstmt.setInt(4,topic.getResearcherId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateTopic(Topic topic) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Topic SET topicName=?,topicSubject=?,topicDescription=?,researcherId=? WHERE id=?";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,topic.getTopicName());
                pstmt.setString(2,topic.getTopicSubject());
                pstmt.setString(3,topic.getTopicDescription());
                pstmt.setInt(4,topic.getResearcherId());
                pstmt.setInt(5,topic.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Topic> loadTopics(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Topic> topicList = new ArrayList<>();
            String sql = "SELECT id,topicName,topicSubject,topicDescription,researcherId FROM Topic;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Topic topic = new Topic();
                           topic.setId(rs.getInt("id"));
                           topic.setTopicName(rs.getString("topicName"));
                           topic.setTopicSubject(rs.getString("topicSubject"));
                           topic.setTopicDescription(rs.getString("topicDescription"));
                           topic.setResearcherId(rs.getInt("researcherId"));
                           topicList.add(topic);
                       }
                tx.commit();
                session.close();
                return topicList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
