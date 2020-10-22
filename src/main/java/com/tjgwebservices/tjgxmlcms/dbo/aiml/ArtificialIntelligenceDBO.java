package com.tjgwebservices.tjgxmlcms.dbo.aiml;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.aiml.ArtificialIntelligence;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtificialIntelligenceDBO {

    public static void saveArtificialIntelligence(ArtificialIntelligence artificialIntelligence){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(artificialIntelligence);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLArtificialIntelligence(ArtificialIntelligence artificialIntelligence) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO ArtificialIntelligence(title, description, algorithmPath, dataSourcePath, dataTargetPath) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,artificialIntelligence.getTitle());
                pstmt.setString(2,artificialIntelligence.getDescription());
                pstmt.setString(3,artificialIntelligence.getAlgorithmPath());
                pstmt.setString(4,artificialIntelligence.getDataSourcePath());
                pstmt.setString(5,artificialIntelligence.getDataTargetPath());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateArtificialIntelligence(ArtificialIntelligence artificialIntelligence) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE ArtificialIntelligence SET title =?, description =?, algorhtimPath=?, dataSourcePath=?, dataTargetPath=? WHERE id = ?";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,artificialIntelligence.getTitle());
                pstmt.setString(2,artificialIntelligence.getDescription());
                pstmt.setString(3,artificialIntelligence.getAlgorithmPath());
                pstmt.setString(4,artificialIntelligence.getDataSourcePath());
                pstmt.setString(5,artificialIntelligence.getDataTargetPath());
                pstmt.setInt(6,artificialIntelligence.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<ArtificialIntelligence> loadArtificialIntelligences(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<ArtificialIntelligence> artificialIntelligenceList = new ArrayList<>();
            String sql = "SELECT id,title,description,algorithmPath,dataSourcePath,dataTargetPath FROM ArtificialIntelligence;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();
                           artificialIntelligence.setId(rs.getInt("id"));
                           artificialIntelligence.setTitle(rs.getString("title"));
                           artificialIntelligence.setDescription(rs.getString("description"));
                           artificialIntelligence.setAlgorithmPath(rs.getString("algorithmPath"));
                           artificialIntelligence.setDataSourcePath(rs.getString("dataSourcePath"));
                           artificialIntelligence.setDataTargetPath(rs.getString("dataSourcePath"));
                           artificialIntelligenceList.add(artificialIntelligence);
                       }
                tx.commit();
                session.close();
                return artificialIntelligenceList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
