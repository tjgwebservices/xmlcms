package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.form.ArticleForm;
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
        articles.add(new Article("Article Title 1", "Article Description 1"));
        articles.add(new Article("Article Title 1", "Article Description 2"));
    }
 
    // Inject via application.properties
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
 
    @RequestMapping(value = { "/error" }, method = RequestMethod.GET)
    public String index() {
            return "error";
    }  
    
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
 
        model.addAttribute("message", message);
         
        return "index";
    }
 
    @RequestMapping(value = { "/articleList" }, method = RequestMethod.GET)
    public String articleList(Model model) {
 
        model.addAttribute("articles", articles);
 
        return "articleList";
    }
 
    @RequestMapping(value = { "/addArticle" }, method = RequestMethod.GET)
    public String addArticleForm(Model model) {
 
        ArticleForm articleForm = new ArticleForm();
        model.addAttribute("articleForm", articleForm);
 
        return "addArticle";
    }
 
    @RequestMapping(value = { "/addArticle" }, method = RequestMethod.POST)
    public String addArticleSave(Model model, //
            @ModelAttribute("articleForm") ArticleForm articleForm) {
 
        String title = articleForm.getTitle();
        String description = articleForm.getDescription();
 
        if (title != null && title.length() > 0 //
                && description != null && description.length() > 0) {
            Article newArticle = new Article(title, description);
            articles.add(newArticle);
 
            return "redirect:/articleList";
        }
        String error = "Title & Description is required!";
        model.addAttribute("errorMessage", error);
        return "addArticle";
    }
 
}