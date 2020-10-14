package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.dbo.ArticleDBO;
import com.tjgwebservices.tjgxmlcms.dbo.SubscriptionDBO;
import com.tjgwebservices.tjgxmlcms.form.ArticleForm;
import com.tjgwebservices.tjgxmlcms.form.LoginForm;
import com.tjgwebservices.tjgxmlcms.form.SocketDisplay;
import com.tjgwebservices.tjgxmlcms.form.SubscriptionForm;
import com.tjgwebservices.tjgxmlcms.model.Article;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscription;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
                return "/display";
            } else {
                error = "Error with fields!";
                model.addAttribute("errorMessage", error);
                return "/login";
            }
            
    }
    
    @RequestMapping(value = { "/articleList" }, method = RequestMethod.GET)
    public String articleList(Model model) {
        articles = ArticleDBO.loadArticles();
        model.addAttribute("articles", articles);
 
        return "articleList";
    }
 
    @RequestMapping(value = { "/addArticle" }, method = RequestMethod.GET)
    public String addArticleForm(Model model) {
 
        ArticleForm articleForm = new ArticleForm();
        SocketDisplay socketDisplay = new SocketDisplay();
        model.addAttribute("articleForm", articleForm);
        model.addAttribute("socketDisplay", socketDisplay);
 
        return "addArticle";
    }
 
    @RequestMapping(value = { "/addArticle" }, method = RequestMethod.POST)
    public String addArticleSave(Model model, //
        @ModelAttribute("articleForm") ArticleForm articleForm) {
        String author = articleForm.getAuthor();
        String authorDate = articleForm.getAuthorDate();
        String title = articleForm.getTitle();
        String description = articleForm.getDescription();
        String content = articleForm.getContent();
 
        if (author != null && author.length() > 0 
                && authorDate != null && authorDate.length() > 0 
                && title != null && title.length() > 0 
                && description != null && description.length() > 0
                && content != null && content.length() > 0) {
            Article newArticle = new Article(author, authorDate, 
                    title, description, content);
            articles.add(newArticle);
            ArticleDBO.saveSQLArticle(newArticle);
            return "redirect:/articleList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addArticle";
    }

    
    @RequestMapping(value = { "/editArticle/{id}" }, method = RequestMethod.GET)
    public String editArticleForm(Model model,
            @PathVariable("id") Integer id) {
 
        ArticleForm articleForm = new ArticleForm();
        ArticleForm articleEditForm = new ArticleForm();
        Article editArticle = articles.stream()
            .filter((article) -> article.getId() == id)
            .collect(Collectors.toList()).get(0);
        articleEditForm.setAuthor(editArticle.getAuthor());
        articleEditForm.setAuthorDate(editArticle.getAuthorDate());
        articleEditForm.setTitle(editArticle.getTitle());
        articleEditForm.setDescription(editArticle.getDescription());
        articleEditForm.setContent(editArticle.getContent());
        model.addAttribute("articleForm", articleForm);
        model.addAttribute("articleEditForm", articleEditForm);
        return "editArticle/{id}";
    }
 
    @RequestMapping(value = { "/editArticle/{id}" }, method = RequestMethod.POST)
    public String editArticleSave(Model model, //
        @ModelAttribute("articleForm") ArticleForm articleForm) {
        String author = articleForm.getAuthor();
        String authorDate = articleForm.getAuthorDate();
        String title = articleForm.getTitle();
        String description = articleForm.getDescription();
        String content = articleForm.getContent();
 
        if (author != null && author.length() > 0 
                && authorDate != null && authorDate.length() > 0 
                && title != null && title.length() > 0 
                && description != null && description.length() > 0
                && content != null && content.length() > 0) {
            Article newArticle = new Article(author, authorDate, 
                    title, description, content);
            articles.add(newArticle);
            ArticleDBO.saveSQLArticle(newArticle);
            return "redirect:/articleList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "editArticle/{id}";
    }

   @RequestMapping(value = { "/addSubscription" }, method = RequestMethod.GET)
    public String addSubscriptionForm(Model model) {
 
        SubscriptionForm subscriptionForm = new SubscriptionForm();
        model.addAttribute("subscriptionForm", subscriptionForm);
        return "addSubscription";
    }
    
   @RequestMapping(value = { "/addSubscription" }, method = RequestMethod.POST)
    public String addSubscriptionSave(Model model, //
        @ModelAttribute("subscriptionForm") SubscriptionForm subscriptionForm) {
        String publisher = subscriptionForm.getPublisher();
        String subscriptionPlan = subscriptionForm.getSubscriptionPlan();
        String topic = subscriptionForm.getTopic();
 
        if (publisher != null && publisher.length() > 0 
                && subscriptionPlan != null && subscriptionPlan.length() > 0 
                && topic != null && topic.length() > 0) {
            SocketSubscription subscription = new SocketSubscription(
                    subscriptionPlan, publisher, topic);
            subscriptions.add(subscription);
            SubscriptionDBO.saveSubscription(subscription);
            return "redirect:/subscriptionList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addSubscription";
    }

    
}