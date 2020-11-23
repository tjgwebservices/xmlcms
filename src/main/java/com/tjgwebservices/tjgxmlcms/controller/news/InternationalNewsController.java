package com.tjgwebservices.tjgxmlcms.controller.news;

import static com.tjgwebservices.tjgxmlcms.controller.consulting.ConsultingController.retrieveConsultantPage;
import com.tjgwebservices.tjgxmlcms.model.Consulting;
import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InternationalNewsController {

    static {
    }
    
    @Autowired
    private ServletContext context;

    @Value("${international.news.title}")
    private String internationalNewsTitle;
 
    @Value("${international.news.description}")
    private String internationalNewsDescription;
    
    @Value("${international.news.topics}")
    private String internationalNewsTopics;

    @RequestMapping(value = { "/international/business" }, method = RequestMethod.GET)
    public String internationalNewsBusiness(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
        return "international/business";
    }

    @RequestMapping(value = { "/international/finance" }, method = RequestMethod.GET)
    public String internationalNewsFinance(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
         
        return "international/finance";
    }

    @RequestMapping(value = { "/international/local" }, method = RequestMethod.GET)
    public String internationalNewsLocal(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
         
        return "international/local";
    }

    @RequestMapping(value = { "/international/news" }, method = RequestMethod.GET)
    public String internationalNewsNews(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
         
        return "international/news";
    }

    @RequestMapping(value = { "/international/politics" }, method = RequestMethod.GET)
    public String internationalNewsPolitics(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
         
        return "international/politics";
    }

    @RequestMapping(value = { "/international/international" }, method = RequestMethod.GET)
    public String internationalNewsWorld(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
         
        return "international/international";
    }

    
}
