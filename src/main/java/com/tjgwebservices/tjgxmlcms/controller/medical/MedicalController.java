package com.tjgwebservices.tjgxmlcms.controller.medical;

import com.tjgwebservices.tjgxmlcms.controller.book.BookController;
import static com.tjgwebservices.tjgxmlcms.controller.consulting.ConsultingController.retrieveConsultantPage;
import com.tjgwebservices.tjgxmlcms.model.Consulting;
import com.tjgwebservices.tjgxmlcms.model.Market;
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
public class MedicalController {

    static {
    }
    
    @Autowired
    private ServletContext context;


    @RequestMapping(value = { "/medical/technologies" }, method = RequestMethod.GET)
    public String medicalTechnologies(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "market.xml";

        Market market = retrieveMarketPage(xmlPath);
        model.addAttribute("market", market);
         
        return "medical/technologies";
    }

    @RequestMapping(value = { "/medical/research" }, method = RequestMethod.GET)
    public String medicalResearch(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "market.xml";

        Market market = retrieveMarketPage(xmlPath);
        model.addAttribute("market", market);
         
        return "medical/research";
    }

    @RequestMapping(value = { "/medical/consulting" }, method = RequestMethod.GET)
    public String medicalConsulting(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "market.xml";

        Market market = retrieveMarketPage(xmlPath);
        model.addAttribute("market", market);
         
        return "medical/consulting";
    }


    public static Market retrieveMarketPage(String xmlPath){
        Market market = new Market();
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource inputSource = new InputSource(xmlPath);
            //Reader reader = inputSource.getCharacterStream();
            //String contents = reader.toString();
            market.setHeading(xpath.evaluate("//heading",inputSource));
            market.setVersion(xpath.evaluate("//version",inputSource));
            market.setHeadingtitle(xpath.evaluate("//headingtitle",inputSource));
            market.setHeadingdescription(xpath.evaluate("//headingdescription",inputSource));
            NodeList nodes = (NodeList)xpath.evaluate("//links/link",
                    inputSource, XPathConstants.NODESET);
            
            for (int i=0; i<nodes.getLength(); i++){
                String link = nodes.item(i).getTextContent();
                market.getLinks().add(link);
            }
            
            nodes = (NodeList)xpath.evaluate("//links/additionallink",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                String link = nodes.item(i).getTextContent();
                market.getAdditionallinks().add(link);
            }
            nodes = (NodeList)xpath.evaluate("//links/sitelink",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                String link = nodes.item(i).getTextContent();
                market.getSitelinks().add(link);
            }
            nodes = (NodeList)xpath.evaluate("//topics/topic",
                    inputSource, XPathConstants.NODESET);
                    for (int i=0; i<nodes.getLength(); i++){
                        Map<String,String> link = new HashMap<String,String>();
                        Node node = nodes.item(i);
                        link.put(xpath.evaluate("subject", node ),
                                xpath.evaluate("description", node ));
                        market.getTopics().add(link);
                    }
            nodes = (NodeList)xpath.evaluate("//topics/topic",
                    inputSource, XPathConstants.NODESET);
                    for (int i=0; i<nodes.getLength(); i++){
                        Map<String,String> topic = new HashMap<String,String>();
                        Node node = nodes.item(i);
                        topic.put(xpath.evaluate("subject", node ),
                                xpath.evaluate("description", node ));
                        market.getTopics().add(topic);
            }
            nodes = (NodeList)xpath.evaluate("//services/service",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> service = new HashMap<String,String>();
                Node node = nodes.item(i);
                service.put(xpath.evaluate("title", node ),
                        xpath.evaluate("description", node ));
                market.getServices().add(service);
            }
            nodes = (NodeList)xpath.evaluate("//services/service",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> service = new HashMap<String,String>();
                Node node = nodes.item(i);
                service.put(xpath.evaluate("title", node ),
                        xpath.evaluate("description", node ));
                market.getServices().add(service);
            }
            nodes = (NodeList)xpath.evaluate("//analyses/analysis",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> analysis = new HashMap<String,String>();
                Node node = nodes.item(i);
                analysis.put(xpath.evaluate("title", node ),
                        xpath.evaluate("description", node ));
                market.getAnalyses().add(analysis);

            }
            nodes = (NodeList)xpath.evaluate("//analyses/analysis",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> analysis = new HashMap<String,String>();
                Node node = nodes.item(i);
                analysis.put(xpath.evaluate("title", node ),
                        xpath.evaluate("description", node ));
                market.getAnalyses().add(analysis);

            }
            nodes = (NodeList)xpath.evaluate("//sessions/session",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> session = new HashMap<String,String>();
                Node node = nodes.item(i);
                session.put(xpath.evaluate("course", node ),
                        xpath.evaluate("description", node ));
                market.getAnalyses().add(session);

            }
            nodes = (NodeList)xpath.evaluate("//sessions/session",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> session = new HashMap<String,String>();
                Node node = nodes.item(i);
                session.put(xpath.evaluate("course", node ),
                        xpath.evaluate("description", node ));
                market.getAnalyses().add(session);

            }
            nodes = (NodeList)xpath.evaluate("//articles/article",
                    inputSource, XPathConstants.NODESET);
            for (int i=0; i<nodes.getLength(); i++){
                Map<String,String> article = new HashMap<String,String>();
                Node node = nodes.item(i);
                article.put(xpath.evaluate("title", node ),
                        xpath.evaluate("section", node ));
                market.getArticles().add(article);

            }

            /*
            nodes = (NodeList)xpath.evaluate("//articles/article",
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
            }*/
            
            return market;
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(BookController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return market;        
    }
    
}
