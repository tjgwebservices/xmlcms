package com.tjgwebservices.tjgxmlcms.controller.article;

import com.tjgwebservices.tjgxmlcms.dbo.ArticleDBO;
import com.tjgwebservices.tjgxmlcms.form.ArticleForm;
import com.tjgwebservices.tjgxmlcms.form.SocketDisplay;
import com.tjgwebservices.tjgxmlcms.model.article.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ArticleController {
    private static List<Article> articles = new ArrayList<Article>();

    static {
    }
 
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/articles/articleList" }, method = RequestMethod.GET)
    public String articleList(Model model) {
        articles = ArticleDBO.loadArticles();
        model.addAttribute("articles", articles);
 
        return "/articles/articleList";
    }
 
    @RequestMapping(value = { "/articles/addArticle" }, method = RequestMethod.GET)
    public String addArticleForm(Model model) {
 
        ArticleForm articleForm = new ArticleForm();
        SocketDisplay socketDisplay = new SocketDisplay();
        model.addAttribute("articleForm", articleForm);
        model.addAttribute("socketDisplay", socketDisplay);
 
        return "articles/addArticle";
    }
 
    @RequestMapping(value = { "/articles/addArticle" }, method = RequestMethod.POST)
    public String addArticleSave(Model model, //
        @ModelAttribute("articleForm") ArticleForm articleForm) {
        articles = ArticleDBO.loadArticles();
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
            return "redirect:/articles/articleList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "articles/addArticle";
    }

    
    @RequestMapping(value = { "/articles/editArticle/{id}" }, method = RequestMethod.GET)
    public String editArticleForm(Model model,
            @PathVariable("id") Integer id) {
        articles = ArticleDBO.loadArticles();
 
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
        return "/articles/editArticle/{id}";
    }
 
    @RequestMapping(value = { "/articles/editArticle" }, method = RequestMethod.POST)
    public String editArticleSave(Model model, //
        @ModelAttribute("articleForm") ArticleForm articleForm) {
        articles = ArticleDBO.loadArticles();
        Integer id = articleForm.getId();
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
            newArticle.setId(id);
            ArticleDBO.updateArticle(newArticle);
            return "redirect:/articles/articleList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "articles/editArticle/{id}";
    }
    
}
