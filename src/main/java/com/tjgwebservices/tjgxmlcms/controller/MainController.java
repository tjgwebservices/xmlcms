package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.dbo.ArticleDBO;
import com.tjgwebservices.tjgxmlcms.form.ArticleForm;
import com.tjgwebservices.tjgxmlcms.form.SocketDisplay;
import com.tjgwebservices.tjgxmlcms.form.SubscriptionForm;
import com.tjgwebservices.tjgxmlcms.model.Article;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
 
    private static List<Article> articles = new ArrayList<Article>();
 
    static {
    }
 
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
 
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

   @RequestMapping(value = { "/addSubscription" }, method = RequestMethod.GET)
    public String addSubscriptionForm(Model model) {
 
        SubscriptionForm subscriptionForm = new SubscriptionForm();
        model.addAttribute("subscriptionForm", subscriptionForm);
        return "addSubscription";
    }
    
   @RequestMapping(value = { "/addSubscription" }, method = RequestMethod.POST)
    public String addSubscriptionSave(Model model, //
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

    
}