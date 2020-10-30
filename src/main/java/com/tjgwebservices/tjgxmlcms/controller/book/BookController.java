package com.tjgwebservices.tjgxmlcms.controller.book;

import com.tjgwebservices.tjgxmlcms.model.BookPage;
import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Controller
public class BookController {

    private static List<BookPage> pages = new ArrayList<>();
    private static String path;

    @Autowired
    private ServletContext context;

    static {
    }    
    
    
    public static List<BookPage> retrievePages(String xmlPath){
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource inputSource = new InputSource(xmlPath);
            //Reader reader = inputSource.getCharacterStream();
            //String contents = reader.toString();
            NodeList nodes = (NodeList)xpath.evaluate("//bookpages/bookpage",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                BookPage bookPage = new BookPage();
                Node node = nodes.item(i);
                bookPage.setTitle(xpath.evaluate("title", node ));
                bookPage.setContent(xpath.evaluate("content", node ));
                pages.add(bookPage);

            }
            return pages;
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(BookController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return pages;        
    }

    public List<BookPage> getPages() {
        return pages;
    }

    public void setPages(List<BookPage> pages) {
        this.pages = pages;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    

}
