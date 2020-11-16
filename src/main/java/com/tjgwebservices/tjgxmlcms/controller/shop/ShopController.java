package com.tjgwebservices.tjgxmlcms.controller.shop;

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
public class ShopController {

    static {
    }
    
    @Autowired
    private ServletContext context;


    @RequestMapping(value = { "/shop/shop" }, method = RequestMethod.GET)
    public String consultingGroups(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "shop/shop";
    }
    
}
