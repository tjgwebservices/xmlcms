package com.tjgwebservices.tjgxmlcms.controller.news;

import static com.tjgwebservices.tjgxmlcms.controller.consulting.ConsultingController.retrieveConsultantPage;
import com.tjgwebservices.tjgxmlcms.model.Consulting;
import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "news/forum";
    }

    @RequestMapping(value = { "/news/news" }, method = RequestMethod.GET)
    public String newsNews(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "news/news";
    }

    @RequestMapping(value = { "/news/freelance" }, method = RequestMethod.GET)
    public String newsFreelance(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "news/freelance";
    }
    
}
