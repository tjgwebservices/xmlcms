package com.tjgwebservices.tjgxmlcms.dbo.research;

import com.tjgwebservices.tjgxmlcms.dbm.HibernateAdmin;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.conn;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.pstmt;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.session;
import static com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject.tx;
import com.tjgwebservices.tjgxmlcms.model.research.Researcher;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResearcherDBO {


    public static void saveSQLResearcher(Researcher researcher) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "INSERT INTO Researcher(researcherFirstName,researcherLastName,researcherDegree,researcherMajor,researcherInstitution,researcherSpecialty) VALUES(?,?,?,?,?,?)";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,researcher.getResearcherFirstName());
                pstmt.setString(2,researcher.getResearcherLastName());
                pstmt.setString(3,researcher.getResearcherDegree());
                pstmt.setString(4,researcher.getResearcherMajor());
                pstmt.setString(5,researcher.getResearcherInstitution());
                pstmt.setString(6,researcher.getResearcherSpecialty());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static void updateResearcher(Researcher researcher) {
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            String sql = "UPDATE Researcher SET researcherFirstName=?,researcherLastName=?,researcherDegree=?,researcherMajor=?,researcherInstitution=?,researcherSpecialty=? WHERE id=?";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,researcher.getResearcherFirstName());
                pstmt.setString(2,researcher.getResearcherLastName());
                pstmt.setString(3,researcher.getResearcherDegree());
                pstmt.setString(4,researcher.getResearcherMajor());
                pstmt.setString(5,researcher.getResearcherInstitution());
                pstmt.setString(6,researcher.getResearcherSpecialty());
                pstmt.setInt(7,researcher.getId());
                pstmt.executeUpdate();
                tx.commit();
                session.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
    }

    public static List<Researcher> loadResearchers(){
            session = HibernateAdmin.getSession();
            tx = session.beginTransaction();
            List<Researcher> researcherList = new ArrayList<>();
            String sql = "SELECT id,researcherFirstName,researcherLastName,researcherDegree,researcherMajor,researcherInstitution,researcherSpecialty FROM Researcher;";
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:memory:articledb?cache=shared");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                       while(rs.next()){
                           Researcher researcher = new Researcher();
                           researcher.setId(rs.getInt("id"));
                           researcher.setResearcherFirstName(rs.getString("researcherFirstName"));
                           researcher.setResearcherLastName(rs.getString("researcherLastName"));
                           researcher.setResearcherDegree(rs.getString("researcherDegree"));
                           researcher.setResearcherMajor(rs.getString("researcherMajor"));
                           researcher.setResearcherInstitution(rs.getString("researcherInstitution"));
                           researcher.setResearcherSpecialty(rs.getString("researcherSpecialty"));
                           researcherList.add(researcher);
                       }
                tx.commit();
                session.close();
                return researcherList;
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
            tx.rollback();
            }
        return null;
    }
    
}
