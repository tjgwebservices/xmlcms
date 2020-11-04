package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.controller.book.BookController;
import com.tjgwebservices.tjgxmlcms.form.LoginForm;
import com.tjgwebservices.tjgxmlcms.model.BookPage;
import com.tjgwebservices.tjgxmlcms.model.article.Article;
import com.tjgwebservices.tjgxmlcms.model.socket.SocketSubscription;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
 
    private static List<Article> articles = new ArrayList<Article>();
    private static List<SocketSubscription> subscriptions = new ArrayList<>();
 
    static {
    }
    
    @Autowired
    private ServletContext context;

    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
    
    
    @RequestMapping("/user")
    @ResponseBody
    public String authenticatedUser(@RequestBody Model model) {
        return "Welcome User";
    }
    
    @RequestMapping("/admin")
    @ResponseBody 
    public String authenticatedAdmin(@RequestBody Model model) {
        return "Welcome Admin";
    }
    
    @RequestMapping(value = { "/error" }, method = RequestMethod.GET)
    public String index() {
            System.out.println("Error index page");
            return "error";
    }  
    
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "catalog.xml";

        List<BookPage> pages = BookController.retrievePages(xmlPath);
        model.addAttribute("pages", pages);
        model.addAttribute("message", message);
         
        return "index";
    }


    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm",loginForm);
         
        return "login";    
        
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.POST)
    public String loginAuthenticate(Model model, 
        @ModelAttribute("loginForm") LoginForm loginForm) {
            String error;
        
            String username = loginForm.getUsername();
            String password = loginForm.getPassword();
            if (username != null && password != null) {
                //return "redirect:/authenticated/user";                
                return "/authenticated/adminPage";
            } else {
                error = "Error with fields!";
                model.addAttribute("errorMessage", error);
                return "/login";
            }
            
    }
}