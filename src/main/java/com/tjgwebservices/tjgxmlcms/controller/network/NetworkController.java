package com.tjgwebservices.tjgxmlcms.controller.network;

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
public class NetworkController {


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


    @RequestMapping(value = { "/network/education" }, method = RequestMethod.GET)
    public String internationalNewsBusiness(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
        return "network/education";
    }

    @RequestMapping(value = { "/network/freelance" }, method = RequestMethod.GET)
    public String internationalNewsFinance(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
         
        return "network/freelance";
    }

    @RequestMapping(value = { "/network/news" }, method = RequestMethod.GET)
    public String internationalNewsLocal(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
        model.addAttribute("internationalNewsTitle", internationalNewsTitle);
        model.addAttribute("internationalNewsDescription", internationalNewsDescription);
        model.addAttribute("internationalNewsTopics", internationalNewsTopics);         
         
        return "network/news";
    }

    
    
}
