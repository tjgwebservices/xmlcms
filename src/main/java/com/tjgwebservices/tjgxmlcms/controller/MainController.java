package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.dbo.ArticleDBO;
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
        articles = ArticleDBO.loadArticles();
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