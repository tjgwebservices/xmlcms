package com.tjgwebservices.tjgxmlcms.dbo.aiml;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.aiml.MachineLearning;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MachineLearningDBO {


    public static void saveMachineLearning(MachineLearning machineLearning){
        try {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            session.save(machineLearning);
            session.flush();
                tx.commit();
                session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void saveSQLMachineLearning(MachineLearning machineLearning) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO MachineLearning(title, description, algorithmPath, dataSourcePath, dataTargetPath) VALUES(?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,machineLearning.getTitle());
                pstmt.setString(2,machineLearning.getDescription());
                pstmt.setString(3,machineLearning.getAlgorithmPath());
                pstmt.setString(4,machineLearning.getDataSourcePath());
                pstmt.setString(5,machineLearning.getDataTargetPath());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateMachineLearning(MachineLearning machineLearning) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE MachineLearning SET title =?, description =?, algorhtimPath=?, dataSourcePath=?, dataTargetPath=? WHERE id = ?";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,machineLearning.getTitle());
                pstmt.setString(2,machineLearning.getDescription());
                pstmt.setString(3,machineLearning.getAlgorithmPath());
                pstmt.setString(4,machineLearning.getDataSourcePath());
                pstmt.setString(5,machineLearning.getDataTargetPath());
                pstmt.setInt(6,machineLearning.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<MachineLearning> loadMachineLearnings(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<MachineLearning> machineLearningList = new ArrayList<>();
            String sql = "SELECT id,title,description,algorithmPath,dataSourcePath,dataTargetPath FROM MachineLearning;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           MachineLearning machineLearning = new MachineLearning();
                           machineLearning.setId(rs.getInt("id"));
                           machineLearning.setTitle(rs.getString("title"));
                           machineLearning.setDescription(rs.getString("description"));
                           machineLearning.setAlgorithmPath(rs.getString("algorithmPath"));
                           machineLearning.setDataSourcePath(rs.getString("dataSourcePath"));
                           machineLearning.setDataTargetPath(rs.getString("dataSourcePath"));
                           machineLearningList.add(machineLearning);
                       }
                tx.commit();
                session.close();
                return machineLearningList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
