package com.tjgwebservices.tjgxmlcms.dbo.research;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.research.Project;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDBO extends DatabaseObject{

    public static void saveSQLProject(Project project) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Topic(topicName,topicSubject,topicDescription,researcherId) VALUES(?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,project.getProjectName());
                pstmt.setString(2,project.getProjectSubject());
                pstmt.setString(3,project.getProjectDescription());
                pstmt.setInt(4,project.getResearcherId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateProject(Project project) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Topic SET topicName=?,topicSubject=?,topicDescription=?,researcherId=? WHERE id =?";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,project.getProjectName());
                pstmt.setString(2,project.getProjectSubject());
                pstmt.setString(3,project.getProjectDescription());
                pstmt.setInt(4,project.getResearcherId());
                pstmt.setInt(5,project.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Project> loadProjects(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Project> projectList = new ArrayList<>();
            String sql = "SELECT id,projectName,projectSubject,projectDescription,researcherId FROM Researcher;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Project project = new Project();
                           project.setId(rs.getInt("id"));
                           project.setProjectName(rs.getString("projectName"));
                           project.setProjectSubject(rs.getString("projectSubject"));
                           project.setProjectDescription(rs.getString("projectDescription"));
                           project.setResearcherId(rs.getInt("researcherId"));
                           projectList.add(project);
                       }
                tx.commit();
                session.close();
                return projectList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
