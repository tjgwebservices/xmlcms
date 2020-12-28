package com.tjgwebservices.tjgxmlcms.controller.article;

import com.tjgwebservices.tjgxmlcms.dbo.article.BlogDBO;
import com.tjgwebservices.tjgxmlcms.form.article.BlogForm;
import com.tjgwebservices.tjgxmlcms.model.article.Blog;
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
public class BlogController {
    private static List<Blog> blogs = new ArrayList<Blog>();

    static {
    }
 
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/articles/blogList" }, method = RequestMethod.GET)
    public String blogList(Model model) {
        blogs = BlogDBO.loadBlogs();
        model.addAttribute("articles", blogs);
 
        return "/articles/blogList";
    }
 
    @RequestMapping(value = { "/articles/addBlog" }, method = RequestMethod.GET)
    public String addBlogForm(Model model) {
 
        BlogForm blogForm = new BlogForm();
        model.addAttribute("blogForm", blogForm);
        return "articles/addBlog";
    }
 
    @RequestMapping(value = { "/articles/addBlog" }, method = RequestMethod.POST)
    public String addBlogSave(Model model, //
        @ModelAttribute("blogForm") BlogForm blogForm) {
        blogs = BlogDBO.loadBlogs();
        String author = blogForm.getAuthor();
        String authorDate = blogForm.getAuthorDate();
        String title = blogForm.getTitle();
        String description = blogForm.getDescription();
        String content = blogForm.getContent();
 
        if (author != null && author.length() > 0 
                && authorDate != null && authorDate.length() > 0 
                && title != null && title.length() > 0 
                && description != null && description.length() > 0
                && content != null && content.length() > 0) {
            Blog newBlog = new Blog(author, authorDate, 
                    title, description, content);
            blogs.add(newBlog);
            BlogDBO.saveSQLBlog(newBlog);
            return "redirect:/articles/blogList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "articles/addBlog";
    }

    
    @RequestMapping(value = { "/articles/editBlog/{id}" }, method = RequestMethod.GET)
    public String editBlogForm(Model model,
            @PathVariable("id") Integer id) {
        blogs = BlogDBO.loadBlogs();
 
        BlogForm blogForm = new BlogForm();
        BlogForm blogEditForm = new BlogForm();
        Blog editBlog = blogs.stream()
            .filter((blog) -> blog.getId() == id)
            .collect(Collectors.toList()).get(0);
        blogEditForm.setAuthor(editBlog.getAuthor());
        blogEditForm.setAuthorDate(editBlog.getAuthorDate());
        blogEditForm.setTitle(editBlog.getTitle());
        blogEditForm.setDescription(editBlog.getDescription());
        blogEditForm.setContent(editBlog.getContent());
        model.addAttribute("blogForm", blogForm);
        model.addAttribute("blogEditForm", blogEditForm);
        return "/articles/editBlog/{id}";
    }
 
    @RequestMapping(value = { "/articles/editBlog" }, method = RequestMethod.POST)
    public String editBlogSave(Model model, //
        @ModelAttribute("blogForm") BlogForm blogForm) {
        blogs = BlogDBO.loadBlogs();
        Integer id = blogForm.getId();
        String author = blogForm.getAuthor();
        String authorDate = blogForm.getAuthorDate();
        String title = blogForm.getTitle();
        String description = blogForm.getDescription();
        String content = blogForm.getContent();
 
        if (author != null && author.length() > 0 
                && authorDate != null && authorDate.length() > 0 
                && title != null && title.length() > 0 
                && description != null && description.length() > 0
                && content != null && content.length() > 0) {
            Blog newBlog = new Blog(author, authorDate, 
                    title, description, content);
            newBlog.setId(id);
            BlogDBO.updateBlog(newBlog);
            return "redirect:/articles/blogList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "articles/editBlog/{id}";
    }

}
