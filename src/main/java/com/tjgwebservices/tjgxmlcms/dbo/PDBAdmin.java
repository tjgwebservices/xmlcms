package com.tjgwebservices.tjgxmlcms.dbo;

import com.tjgwebservices.tjgxmlcms.model.DbConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Controller
public class PDBAdmin {

    private final String url = "jdbc:postgresql://localhost/";
    private final String user = "postgres";
    private final String password = "root";

    @Autowired
    private ServletContext context;

    @Value("${pdb.path}")
    private String xmlPath;
    
    

    public void connectToPDB(){
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource inputSource = new InputSource(xmlPath);
            NodeList nodes = (NodeList)xpath.evaluate("/sqlconfig/databases/database/configuration",
                    inputSource, XPathConstants.NODESET);
            Node node = nodes.item(0);
            DbConfig dbConfig = new DbConfig();
            dbConfig.setHostname(
                    xpath.evaluate("hostname", node )
            );
            dbConfig.setDbname(
                    xpath.evaluate("dbname", node )
            );
            dbConfig.setDbuser(
                    xpath.evaluate("dbuser", node )
            );
            dbConfig.setDbpassword(
                    xpath.evaluate("dbpassword", node )
            );
            dbConfig.setDbport(
                    xpath.evaluate("dbport", node )
            );
            
            String url = "jdbc:postgresql://"+dbConfig.getHostname()+"/"+
                    dbConfig.getDbname();
            String user = dbConfig.getDbuser();
            String password = dbConfig.getDbpassword();
            Connection conn = null;
             try {
                 conn = DriverManager.getConnection(url, user, password);

                 if (conn != null) {
                     System.out.println("Connected to the PostgreSQL server successfully.");
                 } else {
                     System.out.println("Failed to make connection!");
                 }

             } catch (SQLException e) {
                 System.out.println(e.getMessage());
             }            
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(PDBAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

