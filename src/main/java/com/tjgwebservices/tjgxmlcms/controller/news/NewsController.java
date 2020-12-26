package com.tjgwebservices.tjgxmlcms.controller.news;

import static com.tjgwebservices.tjgxmlcms.controller.consulting.ConsultingController.retrieveConsultantPage;
import com.tjgwebservices.tjgxmlcms.model.Consulting;
import com.tjgwebservices.tjgxmlcms.model.RssFeed;
import java.io.File;
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
public class NewsController {

    static {
    }
    
    @Autowired
    private ServletContext context;


    @RequestMapping(value = { "/news/reports" }, method = RequestMethod.GET)
    public String consultingGroups(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "news/reports";
    }

    @RequestMapping(value = { "/news/forum" }, method = RequestMethod.GET)
    public String newsForum(Model model) {

        RssFeed rssFeed = retrieveRssFeed("http://tjgnews.com/rss/");
        model.addAttribute("rssFeed", rssFeed);
         
        return "news/forum";
    }

    @RequestMapping(value = { "/news/news" }, method = RequestMethod.GET)
    public String newsNews(Model model) {
        RssFeed rssFeed = retrieveRssFeed("http://tjgnews.com/rss/");
        model.addAttribute("rssFeed", rssFeed);
         
        return "news/news";
    }

    @RequestMapping(value = { "/news/freelance" }, method = RequestMethod.GET)
    public String newsFreelance(Model model) {
        RssFeed rssFeed = retrieveRssFeed("http://tjgnews.com/rss/");
        model.addAttribute("rssFeed", rssFeed);
         
        return "news/freelance";
    }


    public static RssFeed retrieveRssFeed(String xmlPath){
        RssFeed rssFeed = new RssFeed();
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
                        InputSource inputSource = new InputSource(xmlPath);
            rssFeed.setChannelTitle(xpath.evaluate("//title",inputSource));
            rssFeed.setChannelLink(xpath.evaluate("//link",inputSource));
            rssFeed.setChannelDescription(xpath.evaluate("//description",inputSource));
            
            NodeList nodes = (NodeList)xpath.evaluate("//item",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Node node = nodes.item(i);
                String[] item = new String[] {
                xpath.evaluate("title", node ),
                xpath.evaluate("link", node ),
                xpath.evaluate("description", node )};
                rssFeed.getItems().add(item);
            }

        } catch (XPathExpressionException ex) {
            Logger.getLogger(NewsController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return rssFeed;        
    }
}
