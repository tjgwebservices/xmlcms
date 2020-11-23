package com.tjgwebservices.tjgxmlcms.db;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import com.tjgwebservices.tjgxmlcms.model.DbConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class PostgresqlTests {
    private static DbConfig dbConfig = new DbConfig();
    private static PreparedStatement pstmt;
    private static String connectionUrl;
    private static String user;
    private static String password;

    @Autowired
    private static ServletContext context;
    
    static {
        
    }
    /*
    @BeforeAll
    @Disabled
    public static void setup() throws XPathExpressionException{
        XPath xpath = XPathFactory.newInstance().newXPath();
        String xmlPath = "/sqlconfig.xml";
        InputSource inputSource = new InputSource(xmlPath);
        NodeList nodes = (NodeList)xpath.evaluate("/sqlconfig/databases/database/configuration",
                inputSource, XPathConstants.NODESET);
        Node node = nodes.item(0);

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
        connectionUrl = "jdbc:postgresql://"+dbConfig.getHostname()+":"+dbConfig.getDbport()+"/"+
                dbConfig.getDbname();
        user = dbConfig.getDbuser();
        password = dbConfig.getDbpassword();
    }

    @Test
    @Disabled
    public void testConnection() throws SQLException{
        boolean connectionSuccessful = false;
            
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, user, password);

        if (conn != null) {
            System.out.println("Connected to the PostgreSQL server successfully.");
            connectionSuccessful = true;
        } else {
            System.out.println("Failed to make connection!");
        }

            
        Assertions.assertEquals(connectionSuccessful, true);

    }


    @Test
    @Disabled
    public void testSelectApplications() throws SQLException{
        Connection conn = DriverManager.getConnection(connectionUrl, user, password);
        Statement stmt = conn.createStatement();
        String sql = "SELECT category_id,application FROM ds_applications";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);
        List<String[]> applications = new ArrayList<>();
            while(rs.next()){
                String[] stringArray = {String.valueOf(rs.getInt("category_id")), rs.getString("application")};
                applications.add(stringArray);
            }
        Assertions.assertEquals(22, applications.size());
    }


    @Test
    @Disabled
    public void testSelectCategories() throws SQLException{
        Connection conn = DriverManager.getConnection(connectionUrl, user, password);
        Statement stmt = conn.createStatement();
        String sql = "SELECT id,category FROM ds_applications_categories";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);
        Map<String,String> applications = new HashMap<>();
            while(rs.next()){
                applications.put(String.valueOf(rs.getInt("id")), rs.getString("category"));

            }
        Assertions.assertEquals(8, applications.size());
    }
*/
}
