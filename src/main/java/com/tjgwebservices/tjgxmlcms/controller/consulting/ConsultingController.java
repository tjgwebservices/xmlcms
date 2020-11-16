package com.tjgwebservices.tjgxmlcms.controller.consulting;

import com.tjgwebservices.tjgxmlcms.controller.book.BookController;
import com.tjgwebservices.tjgxmlcms.model.Consulting;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Controller
public class ConsultingController {
    
    
    @Autowired
    private ServletContext context;
    
    

    static {
    }    

    @RequestMapping(value = { "/consulting/groups" }, method = RequestMethod.GET)
    public String consultingGroups(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "consulting/groups";
    }

    @RequestMapping(value = { "/consulting/about" }, method = RequestMethod.GET)
    public String consultingAbout(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "consulting/about";
    }
    
    @RequestMapping(value = { "/consulting/contact" }, method = RequestMethod.GET)
    public String consultingContact(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "consulting/contact";
    }

    
    public static Consulting retrieveConsultantPage(String xmlPath){
        Consulting consulting = new Consulting();
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource inputSource = new InputSource(xmlPath);
            //Reader reader = inputSource.getCharacterStream();
            //String contents = reader.toString();
            consulting.setHeading(xpath.evaluate("//heading",inputSource));
            consulting.setVersion(xpath.evaluate("//version",inputSource));
            consulting.setHeadingtitle(xpath.evaluate("//headingtitle",inputSource));
            consulting.setHeadingdescription(xpath.evaluate("//headingdescription",inputSource));
            consulting.setTheme(xpath.evaluate("//theme",inputSource));
            consulting.setComponenttitle(xpath.evaluate("//componenttitle",inputSource));
            NodeList nodes = (NodeList)xpath.evaluate("//links/link",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> link = new HashMap<String,String>();
                Node node = nodes.item(i);
                link.put(xpath.evaluate("title", node ),
                        xpath.evaluate("url", node ));
                consulting.getLinks().add(link);

            }
            nodes = (NodeList)xpath.evaluate("//tabs/tab",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> tab = new HashMap<String,String>();
                Node node = nodes.item(i);
                tab.put(xpath.evaluate("title", node ),
                        xpath.evaluate("url", node ));
                consulting.getTabs().add(tab);

            }            
            nodes = (NodeList)xpath.evaluate("//sideimages/sideimage",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> sideimage = new HashMap<String,String>();
                Node node = nodes.item(i);
                sideimage.put(xpath.evaluate("title", node ),
                        xpath.evaluate("url", node ));
                consulting.getSideimages().add(sideimage);

            }
            nodes = (NodeList)xpath.evaluate("//mainarticles/mainarticle",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Node node = nodes.item(i);
                String[] article = new String[] {
                xpath.evaluate("title", node ),
                xpath.evaluate("articletext", node ),
                xpath.evaluate("articleimage", node )};
                consulting.getMainarticles().add(article);
            }
            nodes = (NodeList)xpath.evaluate("//subarticles/subarticle",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Node node = nodes.item(i);
                String[] article = new String[] {
                xpath.evaluate("title", node ),
                xpath.evaluate("articletext", node ),
                xpath.evaluate("articleimage", node )};
                consulting.getSubarticles().add(article);
            }
            nodes = (NodeList)xpath.evaluate("//leadarticles/leadarticle",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Node node = nodes.item(i);
                String[] article = new String[] {
                xpath.evaluate("title", node ),
                xpath.evaluate("articletext", node ),
                xpath.evaluate("articleimage", node )};
                consulting.getLeadarticles().add(article);
            }
            
            return consulting;
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(BookController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return consulting;        
    }

}
